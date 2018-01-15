package com.weix.api.weixin.message.reply;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("item")
@Data
public class Article {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
}
