package com.weix.api.weixin.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "weixin.mp")
public class WeixinMpProperties {
    private boolean server;
    private String appid;
    private String secrete;
    private String mch_id;
    private String mch_key;
    private final Session session = new Session();
    private final Api api = new Api();

    @Data
    public static class Session {
        private String host;
        private String url;
        private String token;
        private String encodingAESKey;
    }

    @Data
    public static class Api {
        private String token;
        private String getcallbackip;
        private final Template template = new Template();
        private final User user = new User();
        private final Menu menu = new Menu();
        private final Oauth2 oauth2 = new Oauth2();
        private final Pay pay = new Pay();
        private final Ticket ticket = new Ticket();


        @Data
        public static class Template {
            private String get_industry;
            private String send;
            private Id id;

            @Data
            public static class Id {
                private String subscribe_progress_notify;
            }
        }

        @Data
        public static class User {
            private String get_list;
            private String get_info;
        }

        @Data
        public static class Menu {
            private String create;
        }

        @Data
        public static class Oauth2 {
            private String authorize;
            private String access_token;
            private String userinfo;
        }

        @Data
        public static class Pay {
            private String unifiedorder;
            private String getQRCodeUrl;
        }

        @Data
        public static class Ticket {
            private String gettikcet;
        }
    }

    @PostConstruct
    public void doPostConstruct() {
        BaseApi.init(this);
    }

}
