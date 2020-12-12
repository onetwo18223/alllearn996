package com.bhh.imooc.alllearning996.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author bhh
 * @description Caffeine缓存管理配置
 * @date Created in 2020-12-10 22:24
 * @modified By
 */
@Configuration
@EnableCaching
@Slf4j
public class CaffeineCacheConfig {

    /**
     * CacheManager实现类
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        //缓存集合
        ArrayList<CaffeineCache> caffeineCaches = new ArrayList<>();

        caffeineCaches.add(new CaffeineCache("new-caffeine",
                Caffeine.newBuilder()
                        //对应key最大容纳的缓存数据量
                        .maximumSize(1000)
                        //过期策略
                        //最后一次访问后 120 秒过期
                        .expireAfterAccess(120, TimeUnit.SECONDS)
                        .build()));

        cacheManager.setCaches(caffeineCaches);

        return  cacheManager;
    }
}
