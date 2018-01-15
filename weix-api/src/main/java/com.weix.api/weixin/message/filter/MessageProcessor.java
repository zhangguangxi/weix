package com.weix.api.weixin.message.filter;

/**
 * 消息处理接口
 */
public interface MessageProcessor {
    /**
     * 消息处理
     * @param receiveMessage 已接收的消息
     * @return 待回复的消息
     */
    String process(String receiveMessage);
}
