package com.weix.api.core;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheDemo {

    @Cacheable(value = "cacheTest")
    public String num(){
        return String.valueOf(Math.random());
    }
}
