package com.weix.api.weixin.message.core;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("xml")
@Data
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
}
