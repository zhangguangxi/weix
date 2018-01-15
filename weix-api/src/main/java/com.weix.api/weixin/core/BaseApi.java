package com.weix.api.weixin.core;


import com.alibaba.fastjson.JSON;
import com.weix.api.weixin.core.dto.TicketResponse;
import com.weix.api.weixin.core.dto.TokenResponse;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BaseApi {
    //缓存访问令牌
    private static String access_token;

    //访问令牌过期时间
    private static Date expires_in;

    //ticket
    private static String ticket;

    //ticket 过期日期
    private static Date ticket_expires_in;

    //配置信息
    private static  WeixinMpProperties weixinMpProperties;

    //公共请求参数
    private static Map<String,Object> commonParameters;

    private static Lock lock = new ReentrantLock();


    /**
     * 配置信息静态化，初始化访问令牌
     * @param weixinMpProperties
     */
    public static void init(WeixinMpProperties weixinMpProperties) {
        if(null == BaseApi.weixinMpProperties){
            BaseApi.weixinMpProperties=weixinMpProperties;
            BaseApi.commonParameters = (Map<String, Object>) JSON.toJSON(BaseApi.weixinMpProperties);
        }
    }

    public static Map<String,Object> getCommonParameters(){
        commonParameters.put("access_token",getAccessToken());
        return commonParameters;
    }

    public static  WeixinMpProperties getWeixinMpProperties(){
        return weixinMpProperties;
    }

    public static String getTicket(boolean  create){
        if(create){
            TicketResponse response = refreshTicket();
            ticket = response.getTicket();
            ticket_expires_in = new Date(System.currentTimeMillis()+(response.getExpires_in()-30)*1000);
            return ticket;
        }

        return getTicket();

    }

    public static String getTicket(){
        if(null !=ticket_expires_in && ticket_expires_in.after(new Date())){
            return ticket;
        }
        try{
            lock.lock();
            if(null != ticket_expires_in && ticket_expires_in.after(new Date())){
                return ticket;
            }
            TicketResponse response = refreshTicket();
            ticket = response.getTicket();
            ticket_expires_in = new Date(System.currentTimeMillis()+(response.getExpires_in()-30)*1000);
            return ticket;
        }finally {
            lock.unlock();
        }
    }

    public static TicketResponse refreshTicket(){
        return JSON.parseObject(HttpUtils.get(weixinMpProperties.getApi().getTicket().getGettikcet()),TicketResponse.class);
    }

    public static Object getAccessToken() {
        if(null !=expires_in && expires_in.after(new Date())){
            return access_token;
        }

        lock.lock();
        if(null !=  expires_in && expires_in.after(new Date())){
            return access_token;
        }
        
        TokenResponse response = refreshToken();
        return null;
    }

    private static TokenResponse refreshToken() {
        return null;
    }
}
