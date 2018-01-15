package com.weix.api.weixin.message.concrete;

import com.weix.api.weixin.message.core.MessageRequest;
import com.weix.api.weixin.message.core.MessageResponse;
import com.weix.api.weixin.message.filter.MessageFilterChain;
import com.weix.api.weixin.message.filter.event.SubscribeEventMessageFilter;
import com.weix.api.weixin.message.receive.event.SubscribeEventReceiveMessage;
import com.weix.api.weixin.service.WeixinSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户关注
 */
@Component
public class ConcreteSubscrieEventReceiveMessageFilter implements SubscribeEventMessageFilter{

    @Autowired
    private WeixinSubscribeService subscribeService;

    @Override
    public void doFilter(MessageRequest request, MessageResponse response, MessageFilterChain chain) {
        SubscribeEventReceiveMessage message = request.getMessage(SubscribeEventReceiveMessage.class);
        response.setMessage(subscribeService.subscribe(message));
        chain.doFilter(request,response);
    }
}
