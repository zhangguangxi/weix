package com.weix.api.weixin.message.receive;

import com.weix.api.weixin.message.core.BaseReceiveMesage;
import lombok.Data;

@Data
public class ImageReceiveMessage extends BaseReceiveMesage {
    private String PicUrl;
    private String MediaId;
}
