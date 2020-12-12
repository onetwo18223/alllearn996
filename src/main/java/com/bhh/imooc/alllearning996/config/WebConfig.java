package com.bhh.imooc.alllearning996.config;

import com.bhh.imooc.alllearning996.interceptor.RateLimitInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author bhh
 * @description web配置类
 * @date Created in 2020-12-10 23:21
 * @modified By
 */
@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    /**
     * 全局限流拦截器
     */
    @Resource
    public RateLimitInterceptor rateLimitInterceptor;

    /**
     * 向web中添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置限流拦截器，拦截所有以/api/开头的路径
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/api/**");

    }

    /**
     * 静态资源处理，这里面处理的地址不会被拦截器处理
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //将本地文件映射到服务器，当访问到指定路径时，会返回本地路径下的指定文件
        //配置本地文件夹目录映射
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:F:\\maker\\JAVA" +
                        "\\JavaProject\\workspace01\\alllearning996\\uploads");
        /**
         * 为swagger2做的映射
         */
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
