package com.weix.api.weixin.user;

import com.weix.api.weixin.core.BaseApi;
import com.weix.api.weixin.core.HttpUtils;
import com.weix.api.weixin.core.WeixinMpProperties;
import com.weix.api.weixin.user.dto.GetUserInfoRequest;
import com.weix.api.weixin.user.dto.GetUserInfoResponse;
import com.weix.api.weixin.user.dto.GetUserListRequest;
import com.weix.api.weixin.user.dto.GetUserListResponse;

/**
 * 用户管理
 */
public abstract class UserApi {
    public static GetUserListResponse getList(){
        return HttpUtils.get(apiUrl().getGet_list(),GetUserListResponse.class);
    }

    public static GetUserListResponse getList(String next_openid){
        GetUserListRequest request = new GetUserListRequest();
        request.setNext_openid(next_openid);
        return getList(request);
    }

    private static GetUserListResponse getList(GetUserListRequest request) {
        return HttpUtils.get(apiUrl().getGet_list(),request,GetUserListResponse.class);
    }


    public static GetUserInfoResponse getInfo(String openid){
        GetUserInfoRequest request = new GetUserInfoRequest();
        request.setOpenid(openid);
        return  getInfo(request);
    }

    private static GetUserInfoResponse getInfo(GetUserInfoRequest request) {
        return HttpUtils.get(apiUrl().getGet_info(),request,GetUserInfoResponse.class);
    }

    private static WeixinMpProperties.Api.User apiUrl(){
        return BaseApi.getWeixinMpProperties().getApi().getUser();
    }
}