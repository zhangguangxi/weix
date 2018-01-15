package com.weix.api.weixin.service;

import com.weix.api.constants.SysUserConstants;
import com.weix.api.weixin.message.core.BaseReplyMessage;
import com.weix.api.weixin.message.core.MessageFactory;
import com.weix.api.weixin.message.filter.event.UnSubscribeEventReceiveMessage;
import com.weix.api.weixin.message.receive.event.SubscribeEventReceiveMessage;
import com.weix.api.weixin.message.reply.TextReplyMessage;
import com.weix.service.domain.SysUser;
import com.weix.service.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class WeixinSubscribeService{
    @Autowired
    private SysUserRepository sysUserRepositor;

    /**
     * 用户关注微信公众号
     * @param receiveMessage  用户关注后，微信发送的通知数据
     * @return
     */
    public BaseReplyMessage subscribe(SubscribeEventReceiveMessage receiveMessage){
        TextReplyMessage replyMessage = MessageFactory.newReplayMessage(TextReplyMessage.class, receiveMessage);
        replyMessage.setContent("终于等到你: "+replyMessage.getToUserName());
        return replyMessage;
    }

    /**
     * 用户取消关注微信公众号
     * @param receiveMessage
     * @return
     */
    public BaseReplyMessage unSubscribe(UnSubscribeEventReceiveMessage receiveMessage){
        //检查用不账号是否已绑定微信公众号
        SysUser dbUser = sysUserRepositor.findByWeixinId(receiveMessage.getFromUserName());
        if(null != dbUser){
            //解除绑定
            sysUserRepositor.updateWeixinStatus(dbUser.getId(),null, SysUserConstants.WeixinStatus.CANCEL_BOUND.ordinal());
        }
        return null;
    }
}
