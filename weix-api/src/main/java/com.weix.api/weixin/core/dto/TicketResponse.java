package com.weix.api.weixin.core.dto;

import lombok.Data;

@Data
public class TicketResponse {
    private String ticket;
    private Integer expires_in;
}
