package com.weix.api.weixin.core;

import com.alibaba.fastjson.JSON;
import com.weix.commons.XStreamUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class HttpUtils {
    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON=MediaType.parse("application/json;charset=utf-8");

    /**
     * GET 请求
     * @param url  请求路径
     * @return
     */
    public static String get(String url){
        return get(url,(Map<String,Object>)null);
    }

    /**
     * GET 请求
     * @param url           请求路径
     * @param parameters    请求参数
     * @return
     */
    public static String get(String url,Map<String,Object>parameters){
        return exec(new Request.Builder().url(parseUrlParameters(url,parameters)).build());
    }

    /**
     * GET 请求
     * @param  url       请求路径
     * @param resultType Class类型
     * @param <T>
     * @return
     */
    public static <T> T get(String url,Class<T> resultType){
        return get(url,null,resultType);
    }

    /**
     * GET请求
     * @param url           请求路径
     * @param parameters    参数
     * @param resultType    Class类型
     * @param <T>
     * @return
     */
    public static <T> T get(String url, Map<String, Object> parameters, Class<T> resultType) {
        return exec(new Request.Builder().url(parseUrlParameters(url, parameters)).build(), resultType);
    }

    public static <T> T get(String url, Object parameters, Class<T> resultType) {
        return get(url, (Map<String, Object>) JSON.toJSON(parameters), resultType);
    }

    /**
     * POST请求
     * @param url  请求路径
     * @param body 请求参数
     * @return
     */
    public static String post(String url,Object body){
        return post(url,JSON.toJSONString(body));
    }

    /**
     * POST请求
     * @param url   请求路径
     * @param body  请求参数
     * @return
     */
    public static String post(String url,String body){
        return exec(new Request.Builder().url(parseUrlParameters(url)).post(RequestBody.create(MEDIA_TYPE_JSON,body)).build());
    }

    /**
     * POST 请求
     * @param url           请求路径
     * @param body          请求参数
     * @param resultType    返回类型
     * @param <T>
     * @return
     */
    public static <T> T post(String url,String body,Class<T> resultType){
        return exec(new Request.Builder().url(parseUrlParameters(url)).post(RequestBody.create(MEDIA_TYPE_JSON,body)).build(),resultType);
    }

    /**
     * POST 请求
     * @param url           请求路径
     * @param body          请求参数
     * @param resultType    返回类型
     * @param <T>
     * @return
     */
    public static <T> T post(String url,Object body,Class<T> resultType){
        return post(url,JSON.toJSONString(body),resultType);
    }


    /**
     * POST 请求
     * @param url           请求路径
     * @param body          请求参数
     * @param resultType    返回类型
     * @param <T>
     * @return
     */
    public static <T> T postXml(String url,Object body,Class<T> resultType){
        String xml = XStreamUtils.toXml(body);
        System.out.println("xml==>"+xml);
        return post(url,xml,resultType);
    }

    private static <T> T exec(Request request,Class<T> resultType){
        String result = exec(request).trim();
        if(result.startsWith("<")){
            try {
                T t = resultType.newInstance();
                XStreamUtils.toObject(result,t);
                return t;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return JSON.parseObject(result,resultType);
    }

    private static String exec(Request request) {
        try {
            System.out.println(request.method()+":"+request.url());
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //解析路径中的参数
    private static String parseUrlParameters(String url, Map<String,Object> urlParams){
        if(url.matches(".*\\{[^{}]+}.*")){
            LinkedHashMap<String, Object> commonParameters = new LinkedHashMap<>(BaseApi.getCommonParameters());
            if(null != urlParams && !urlParams.isEmpty()){
                for (Map.Entry<String, Object> entry : urlParams.entrySet()) {
                    if(null != entry.getValue()){
                        commonParameters.put(entry.getKey(),entry.getValue());
                    }
                }
            }
            for (Map.Entry<String, Object> entry : commonParameters.entrySet()) {
                url=url.replace("{"+entry.getKey()+"}",String.valueOf(entry.getValue()));
            }
        }

        return url;
    }

    private static String parseUrlParameters(String url){
        return parseUrlParameters(url,null);
    }
}
