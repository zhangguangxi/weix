package com.weix.api.weixin.user.dto;

import com.weix.api.weixin.core.Response;
import lombok.Data;

import java.util.List;

@Data
public class GetUserListResponse extends Response{
    private Long total;
    private Long count;
    private Data data;
    private String next_openid;

    @lombok.Data
    private static class Data{
        private List<String> openid;


    }
}
