package com.weix.api.weixin.user.dto;

import com.weix.api.weixin.core.Response;
import lombok.Data;

import java.util.List;

@Data
public class GetUserInfoResponse extends Response{
    /*
    {
   "subscribe": 1,
   "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
   "nickname": "Band",
   "sex": 1,
   "language": "zh_CN",
   "city": "广州",
   "province": "广东",
   "country": "中国",
   "headimgurl":  "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4
eMsv84eavHiaiceqxibJxCfHe/0",
  "subscribe_time": 1382694957,
  "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
  "remark": "",
  "groupid": 0,
  "tagid_list":[128,2]
}

   subscribe  用户
   openid     用户的标识，对当前公众号唯一
   nickname   用户的昵称
   sex        用户性别
   city       用户所在城市
   country    用户所在国家
   province   用户所在省份
   language   用户的语言，简体中文
   headimgurl 用户头像最后一个数值代表正方形头像大小(0,46,64,96,132数值可选，0代表640*640正方形头像)用户没有头像是该项为空。
   unionid    只有在用户将公众号绑定到微信开发平台账号
   remark     公众号运营者对粉丝的备注，公众号运营者可在微信
   groupid    用户所在分组ID(兼容旧的用户分组接口)
   tagid_list 用户被打上的标签ID列表
*/
    private Integer subscribe;
    private String openid;
    private String nickname;
    private Integer sex;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headimgurl;
    private Long subscribe_time;
    private String unionid;
    private String remark;
    private Integer groupid;
    private List<Integer> tagid_lst;

}
