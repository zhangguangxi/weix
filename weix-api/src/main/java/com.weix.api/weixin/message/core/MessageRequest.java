package com.weix.api.weixin.message.core;

/**
 * 消息请求
 */
public class MessageRequest {
    //原始消息
    private String original;
    //将原始消息转换为对象(xml -> object)
    private BaseMessage message;

    public MessageRequest(String original){
        this.original = original;
    }

    public MessageRequest(String original,BaseMessage baseMessage){
        this.original = original;
        this.message = baseMessage;
    }

    public String getOriginal(){
        return original;
    }

    public <T extends BaseMessage> T getMessage(Class<T> messageType){
        return (T)message;
    }


    public BaseMessage getMessage() {
        return message;
    }
}
