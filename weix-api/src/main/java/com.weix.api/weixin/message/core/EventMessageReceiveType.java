package com.weix.api.weixin.message.core;

import com.weix.api.weixin.message.filter.EventMessageFilter;
import com.weix.api.weixin.message.filter.event.*;
import com.weix.api.weixin.message.receive.event.*;

/**
 * 事件消息接收类型
 */
public enum  EventMessageReceiveType {
    subscribe(SubscribeEventReceiveMessage.class,SubscribeEventMessageFilter.class),
    unsubscribe(UnSubscribeEventReceiveMessage.class,UnSubscribeEventMessageFilter.class),
    SCAN(ScanEventReceiveMessage.class,ScanEventMessageFilter.class),
    LOCATION(LocationEventReceiveMessage.class,LocationEventMessageFilter.class),
    CLICK(ClickEventReceiveMessage.class,ClickEventMessageFilter.class),
    VIEW(ViewEventReceiveMessage.class,ViewEventMessageFilter.class),
    TEMPLATESENDJOBFINISH(TemplatesendjobfinishEventReceiveMessage.class,TemplatesendjobfinishEventMessageFilter.class),
    ;


    //消息类型
    private Class<? extends  BaseEventReceiveMessage> messageType;
    //消息处理器类型
    private Class<? extends EventMessageFilter> filterType;

    EventMessageReceiveType(Class<? extends BaseEventReceiveMessage> messageType, Class<? extends EventMessageFilter> filterType) {
        this.messageType = messageType;
        this.filterType = filterType;
    }


    public Class<? extends BaseEventReceiveMessage> messageType(){
        return messageType;
    }

    public Class<? extends  EventMessageFilter> filterType(){
        return filterType;
    }

    public  static EventMessageReceiveType valueOf(Class<? extends EventMessageFilter> filterType){
        for (EventMessageReceiveType value : values()) {
            if(value.filterType.isAssignableFrom(filterType)){
                return value;
            }
        }
        return null;
    }
}
