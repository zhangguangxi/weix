package com.weix.api.weixin.message.core;

public class MessageFactory {
    /**
     * 根据消息类型创建消息对象
     * @param messageType
     * @param <T>
     * @return
     */
    public static <T extends BaseReplyMessage> T newReplayMessage(Class<T> messageType){
        try {
            T t = messageType.newInstance();
            t.setMsgType(MessageReplyType.valueOf(messageType).name());
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据消息类型，接受消息类型创建回复消息对象
     * @param messageType
     * @param receiveMessage
     * @param <T>
     * @return
     */
    public static <T extends BaseReplyMessage> T newReplayMessage(Class<T> messageType,BaseMessage receiveMessage){
        T t = newReplayMessage(messageType);
        t.setFromUserName(receiveMessage.getToUserName());
        t.setToUserName(receiveMessage.getFromUserName());
        t.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
        return t;
    }
}
