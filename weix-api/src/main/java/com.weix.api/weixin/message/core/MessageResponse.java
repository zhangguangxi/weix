package com.weix.api.weixin.message.core;

public class MessageResponse {
    private BaseMessage message;

    public BaseMessage getMessage() {
        return message;
    }

    public void setMessage(BaseMessage message){
        this.message = message;
    }
}
