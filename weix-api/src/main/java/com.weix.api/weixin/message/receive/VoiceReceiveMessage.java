package com.weix.api.weixin.message.receive;

import com.weix.api.weixin.message.core.BaseReceiveMesage;
import lombok.Data;

@Data
public class VoiceReceiveMessage extends BaseReceiveMesage {
    private String MediaId;
    private String Format;
    private String Recognition;
}
