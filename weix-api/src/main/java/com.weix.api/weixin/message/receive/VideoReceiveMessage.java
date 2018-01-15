package com.weix.api.weixin.message.receive;

import com.weix.api.weixin.message.core.BaseReceiveMesage;
import lombok.Data;

@Data
public class VideoReceiveMessage extends BaseReceiveMesage {
    private String MediaI;
    private String ThumbMediaId;
}
