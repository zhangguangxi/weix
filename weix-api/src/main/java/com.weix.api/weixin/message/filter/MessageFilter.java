package com.weix.api.weixin.message.filter;

import com.weix.api.weixin.message.core.MessageRequest;
import com.weix.api.weixin.message.core.MessageResponse;

public interface MessageFilter {
    void doFilter(final MessageRequest request, final MessageResponse response, final MessageFilterChain chain);
}
