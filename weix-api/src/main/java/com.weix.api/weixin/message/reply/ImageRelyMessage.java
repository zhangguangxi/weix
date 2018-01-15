package com.weix.api.weixin.message.reply;

import com.weix.api.weixin.message.core.BaseReplyMessage;
import lombok.Data;

@Data
public class ImageRelyMessage extends BaseReplyMessage {
    private Image Image;
}
