package com.weix.api.weixin.message.core;

import lombok.Data;

@Data
public class BaseReceiveMesage extends BaseMessage{
    private String MsgId;
}
