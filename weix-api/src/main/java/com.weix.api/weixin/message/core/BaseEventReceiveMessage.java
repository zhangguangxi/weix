package com.weix.api.weixin.message.core;

import lombok.Data;

@Data
public class BaseEventReceiveMessage extends  BaseMessage {
    private String Event;
    private String EventKey;
}
