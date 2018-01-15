package com.weix.api.weixin.message.filter;

import com.weix.api.weixin.message.core.*;
import com.weix.commons.XStreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认的消息处理接口
 */
@Component
public abstract  class DefaultMessageProcessor implements MessageProcessor{

    @Autowired
    private MessageFilterChain filterChain;


    @Override
    public String process(String original) {
        //接收到的消息
        BaseMessage receiveMessage = new BaseMessage();
        XStreamUtils.toObject(original,receiveMessage);
        //消息类型
        MessageReceiveType messageReceiveType = MessageReceiveType.valueOf(receiveMessage.getMsgType());

        try {
            //事件消息类型
            if(MessageReceiveType.event == messageReceiveType){
                //事件消息接收类型
                BaseEventReceiveMessage baseEventReceiveMessage = new BaseEventReceiveMessage();
                //将原始消息转换成事件消息接收类型
                XStreamUtils.toObject(original,baseEventReceiveMessage);
                EventMessageReceiveType eventMessageReceiveType = EventMessageReceiveType.valueOf(baseEventReceiveMessage.getEvent());
                receiveMessage  = eventMessageReceiveType.messageType().newInstance();
            }else{
                //普通消息类型
                 receiveMessage = messageReceiveType.messageType().newInstance();
                 //重置过滤器计数器
                filterChain.resetFilterCounter();
                //消息过滤
                MessageRequest request = new MessageRequest(original, receiveMessage);
                MessageResponse response = new MessageResponse();
                filterChain.doFilter(request,response);
                if(null != response.getMessage()){
                    return XStreamUtils.toXml(response.getMessage());
                }
            }
        } catch (InstantiationException  |IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}
