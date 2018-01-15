package com.weix.api.weixin.user.dto;

import com.weix.api.weixin.core.Request;
import lombok.Data;


@Data
public class GetUserListRequest extends Request {
    private String next_openid;
}
