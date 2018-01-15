package com.weix.api.weixin;

import com.weix.api.weixin.core.BaseApi;
import com.weix.api.weixin.core.WeixinMpProperties;
import com.weix.api.weixin.message.filter.MessageProcessor;
import com.weix.api.weixin.user.UserApi;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Controller
@RequestMapping("${weixin.mp.session.url}")
public class WeiXinSessionController {
    @Autowired
    private WeixinMpProperties weixinMpProperties;

    @Autowired
    private MessageProcessor messageProcessor;

    /**
     * 基础配置认证
     * @param verfiyRequest
     * @param response
     * @throws IOException
     */
    @GetMapping
    public void verify(VerifyRequest verfiyRequest, HttpServletResponse response) throws IOException{
        verfiyRequest.setToken(weixinMpProperties.getSession().getToken());
        PrintWriter writer = response.getWriter();
        boolean signature =  verifySignature(verfiyRequest);
        writer.write(signature?verfiyRequest.getEchostr():"Verification signature failed");
        writer.close();
    }

    /**
     * 接受微信发送过来的消息
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping
    public void messageProcess(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String message = IOUtils.toString(request.getReader());
        String result = messageProcessor.process(message);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.flush();
    }

    @GetMapping("/users")
    @ResponseBody
    public Object getUsers(){
        return UserApi.getList();
    }

    @GetMapping("/token")
    @ResponseBody
    public Object getToken(){
        return BaseApi.getAccessToken();
    }

    @GetMapping("/users/{openid}")
    @ResponseBody
    public Object getUserInfo(@PathVariable("openid")String openid){
        return UserApi.getInfo(openid);
    }


    /**
     * 签名校验
     *
     */

    /**
     *
     * @param verfiyRequest
     * @return
     */
    private static boolean verifySignature(VerifyRequest verfiyRequest) {
        String token  = verfiyRequest.getToken();
        String timestamp = verfiyRequest.getTimestamp();
        String nonce = verfiyRequest.getNonce();
        String[] verifyContent = {token,timestamp,nonce};
        Arrays.sort(verifyContent);
        StringBuilder sb = new StringBuilder();
        for (String s : verifyContent) {
            sb.append(s);
        }
        return DigestUtils.sha1Hex(sb.toString()).equals(verfiyRequest.getSignature());
    }

    @Data
    private static class VerifyRequest {
        /**
         * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
         */
        private String signature;

        //时间戳
        private String timestamp;

        //随机数
        private String nonce;

        //随机字符串
        private String echostr;

        //开发者填写的token
        private String token;
    }
}
