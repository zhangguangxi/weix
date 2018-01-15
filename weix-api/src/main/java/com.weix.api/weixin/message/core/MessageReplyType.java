package com.weix.api.weixin.message.core;

import com.weix.api.weixin.message.reply.*;

public enum MessageReplyType {
    text(TextReplyMessage.class),
    image(ImageRelyMessage.class),
    voice(VoiceRepyMessage.class),
    video(VideoReplyMessage.class),
    music(MusicRepayMessage.class),
    news(NewsReplyMessage.class)
    ;
    private Class<? extends BaseReplyMessage> messageType;

    MessageReplyType(Class<? extends BaseReplyMessage> messageType) {
        this.messageType = messageType;
    }

    public static MessageReplyType valueOf(Class<? extends  BaseReplyMessage> messageType){
        MessageReplyType[] values = MessageReplyType.values();
        for (MessageReplyType value : values) {
            if(value.messageType == messageType){
                return value;
            }
        }
        return null;
    }
}
