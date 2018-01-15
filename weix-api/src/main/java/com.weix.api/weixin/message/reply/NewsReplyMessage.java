package com.weix.api.weixin.message.reply;

import com.weix.api.weixin.message.core.BaseReplyMessage;
import lombok.Data;

import java.util.List;

@Data
public class NewsReplyMessage extends BaseReplyMessage {
    private String ArticleCount;
    private List<Article> Articles;
}
