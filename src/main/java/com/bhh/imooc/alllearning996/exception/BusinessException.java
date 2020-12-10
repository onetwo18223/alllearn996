package com.bhh.imooc.alllearning996.exception;

import lombok.Getter;

/**
 * @author bhh
 * @description 业务类异常
 * @date Created in 2020-12-10 19:31
 * @modified By
 */
public class BusinessException extends RuntimeException{

    /**
     * 异常编码
     */
    @Getter
    private final String code;

    /**
     * 根据枚举创建业务类异常
     * @param error
     */
    public BusinessException(CodeEnum error){
        super(error.getMessage());
        this.code = error.getCode();
    }

    /**
     * 自定义消息体创建业务类异常
     * @param error
     * @param message
     */
    public BusinessException(CodeEnum error, String message){
        super(message);
        this.code = error.getCode();
    }

    /**
     * 根据异常创建业务类异常
     * @param error
     * @param cause
     */
    public BusinessException(CodeEnum error, Throwable cause){
        super(cause);
        this.code = error.getCode();
    }
}
