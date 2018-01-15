package com.weix.service.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_SYS_USER")
@Data
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "id_generator")
    @TableGenerator(name = "id_generator",table = "IDPROVIDER",pkColumnName = "name",valueColumnName = "id",pkColumnValue = "TB_SYS_USER",allocationSize = 1)
    private Long id;

    private String userName;

    private String name;

    private String password;

    private String sex;

    private String mobile;

    private String mobileCheck;

    private String email;

    private Integer emailCheck;

    private String userType;

    private Integer status;

    private Long centerId;

    private Long stationId;

    private Long expId;

    private String area;

    private String depart;

    private BigDecimal balance;

    private String memo;

    private Integer weixin;

    private String weixinId;

    private String weixinAccount;

    private String createTime;

}
