package com.weix.api.weixin.message.filter;

import com.weix.api.weixin.message.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageFilterChain {
    //已经注册的消息过滤器
    private List<MessageFilter> messageFilters;

    //消息过滤器分类存储
    private Map<String,List<MessageFilter>> messageFilterMap;

    //过滤器计数器
    private Map<String,Integer> filterCounter;

    //过滤器计数器本地线程
    private ThreadLocal<Map<String,Integer>> filterCounterThreadLocal = new ThreadLocal<>();

    @Autowired
    public MessageFilterChain(List<MessageFilter> messageFilters){
        this.messageFilters = messageFilters;
        //对过滤器进行分类
        this.messageFilterMap=classify(messageFilters);
        //初始化过滤计数器
        this.filterCounter = new LinkedHashMap<>();
        for (Map.Entry<String, List<MessageFilter>> entry : this.messageFilterMap.entrySet()) {
            this.filterCounter.put(entry.getKey(),entry.getValue().size());

        }
    }

    void resetFilterCounter(){
        this.filterCounterThreadLocal.set(new LinkedHashMap<>(filterCounter));
    }

    /**
     * 过滤器分类
     * @param messageFilters
     * @return
     */
    private Map<String,List<MessageFilter>> classify(List<MessageFilter> messageFilters) {
        Map<String, List<MessageFilter>> messageFilterMap = new LinkedHashMap<>();
        for (MessageFilter messageFilter : messageFilters) {
            //事件类型过滤器
            if(EventMessageFilter.class.isAssignableFrom(messageFilter.getClass())){
                EventMessageFilter eventMessageFilter = (EventMessageFilter) messageFilter;
                EventMessageReceiveType eventReceiveType = EventMessageReceiveType.valueOf(eventMessageFilter.getClass());
                if(null != eventReceiveType){
                    String name = eventReceiveType.name();
                    if(messageFilterMap.containsKey(name)){
                        messageFilterMap.get(name).add(messageFilter);
                        continue;
                    }
                    List<MessageFilter> filters = new ArrayList<>();
                    filters.add(messageFilter);
                    messageFilterMap.put(name,filters);
                    continue;
                }

            }
            MessageReceiveType receiveType = MessageReceiveType.valueOf(messageFilter.getClass());
            if(receiveType != null){
                String name = receiveType.name();
                if(messageFilterMap.containsKey(name)){
                    messageFilterMap.get(name).add(messageFilter);
                    continue;
                }
                List<MessageFilter> filters = new ArrayList<>();
                filters.add(messageFilter);
                messageFilterMap.put(name,filters);
                continue;
            }
            //未归类的过滤器
            throw new RuntimeException("未归类的过滤器: "+messageFilter.getClass());
        }
        return messageFilterMap;
    }

    public  void doFilter(MessageRequest request,MessageResponse response){
        //微信传入的消息类型
        BaseMessage message = request.getMessage();
        String msgType = message.getMsgType();
        //转为内部定义的没消息类型
        MessageReceiveType receiveType = MessageReceiveType.valueOf(msgType);
        //如果消息类型为:事件类型消息，则把消息类型定位事件名称，否则就是一般的消息类型名称
       String messageType = (MessageReceiveType.event == receiveType)?((BaseEventReceiveMessage)message).getEvent():msgType;
        //根据消息类型获取对应的过滤器
        List<MessageFilter> messageFilters = this.messageFilterMap.get(messageType);
        //根据消息类型获取对应的过滤器计数器
        Integer count = this.filterCounterThreadLocal.get().get(messageType);
        //如果获取的过滤器集合不为空，并且过滤器计数器>0，则说明还有可继续执行的计数器
        if(null != messageFilters && !messageFilters.isEmpty() && count >0){
            count --;
            this.filterCounterThreadLocal.get().put(messageType,count);
            messageFilters.get(messageFilters.size() -(count+1)).doFilter(request,response,this);
        }
    }


}
