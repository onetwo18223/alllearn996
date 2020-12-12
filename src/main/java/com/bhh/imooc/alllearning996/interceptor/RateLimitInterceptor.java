package com.bhh.imooc.alllearning996.interceptor;

import com.bhh.imooc.alllearning996.exception.BusinessException;
import com.bhh.imooc.alllearning996.exception.CodeEnum;
import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bhh
 * @description 全局限流拦截器
 * @date Created in 2020-12-10 23:10
 * @modified By
 */
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    /**
     * 限流器实例（限制每秒请求次数为10）
     */
    private static final RateLimiter rateLimiter =
            RateLimiter.create(1);

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        //尝试获取链接失败
        if (!rateLimiter.tryAcquire()) {
            log.error("系统被限流");

            throw new BusinessException(CodeEnum.RATE_LIMIT_Failure);
        }

        return true;
    }
}
