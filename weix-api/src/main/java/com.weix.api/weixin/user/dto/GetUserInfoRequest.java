package com.weix.api.weixin.user.dto;

import com.weix.api.weixin.core.Request;
import lombok.Data;

@Data
public class GetUserInfoRequest extends Request{
    private String openid;
}
