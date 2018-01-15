package com.weix.api.weixin.message.receive;

import com.weix.api.weixin.message.core.BaseReceiveMesage;
import lombok.Data;

@Data
public class LocationReceiveMessage extends BaseReceiveMesage {
    private String Location_x;
    private String Location_Y;
    private String Scale;
    private String Label;
}
