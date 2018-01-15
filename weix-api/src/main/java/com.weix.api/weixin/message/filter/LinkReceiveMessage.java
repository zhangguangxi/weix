package com.weix.api.weixin.message.filter;

import com.weix.api.weixin.message.core.BaseReceiveMesage;
import lombok.Data;

@Data
public class LinkReceiveMessage extends BaseReceiveMesage {
    private String Title;
    private String Description;
    private String Url;
}
