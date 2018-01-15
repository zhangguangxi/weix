package com.weix.api.weixin.message.receive;

import com.weix.api.weixin.message.core.BaseReceiveMesage;
import lombok.Data;

@Data
public class ShortvideoReceiveMessage extends BaseReceiveMesage {
    private String MediaId;
    private String ThumbMediaId;

}
