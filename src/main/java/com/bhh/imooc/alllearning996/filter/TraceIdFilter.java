package com.bhh.imooc.alllearning996.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author bhh
 * @description TraceId过滤器
 * @date Created in 2020-12-11 8:39
 * @modified By
 */
@WebFilter(urlPatterns = "/*")
@Order(1)
@Slf4j
public class TraceIdFilter implements Filter {

    /**
     * traceId常量
     */
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        //尝试获取traceId
        String traceId = servletRequest.getParameter(TRACE_ID);

        //为空赋值
        if (traceId == null || traceId.length() == 0) {
            traceId = UUID.randomUUID().toString();
        }

        //存储traceId到MDC
        //MDC可以看作是一个和线程绑定的map集合，我们可以随时获取MDC数据信息
        MDC.put(TRACE_ID, traceId);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
