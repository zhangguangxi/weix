package com.weix.api.weixin.message.core;

import com.weix.api.weixin.message.filter.*;
import com.weix.api.weixin.message.receive.*;

public enum  MessageReceiveType {
    text(TextReceiveMessage.class,TextMessageFilter.class),
    image(ImageReceiveMessage.class,ImageMessageFilter.class),
    voice(VoiceReceiveMessage.class,VoiceMessageFilter.class),
    video(VideoReceiveMessage.class,VideoMessageFilter.class),
    shortideo(ShortvideoReceiveMessage.class,ShortMessageFilter.class),
    location(LocationReceiveMessage.class,LocationMessageFilter.class),
    link(LinkReceiveMessage.class,LinkMessageFilter.class),
    event(null,null)
    ;

    //消息类型
    private Class<? extends BaseReceiveMesage> messageType;
    //消息处理器类型
    private Class<? extends MessageFilter> filterType;

    MessageReceiveType(Class<? extends  BaseReceiveMesage> messageType,Class<? extends  MessageFilter> filterType){
        this.messageType = messageType;
        this.filterType = filterType;
    }

    public Class<? extends BaseReceiveMesage> messageType(){
        return messageType;
    }

    public Class<? extends  MessageFilter> filterType(){
        return filterType;
    }

    public static MessageReceiveType valueOf(Class<? extends MessageFilter> filterType){
        for (MessageReceiveType value : values()) {
            if(value.filterType().isAssignableFrom(filterType)){
                return  value;
            }
        }
        return  null;
    }
}
