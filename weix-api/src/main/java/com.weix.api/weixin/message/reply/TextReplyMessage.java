package com.weix.api.weixin.message.reply;

import com.weix.api.weixin.message.core.BaseReplyMessage;
import lombok.Data;

@Data
public class TextReplyMessage extends BaseReplyMessage {
    private String content;
}
