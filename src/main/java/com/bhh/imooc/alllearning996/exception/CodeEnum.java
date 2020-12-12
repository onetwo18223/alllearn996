package com.bhh.imooc.alllearning996.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author bhh
 * @description 异常编码枚举
 * @date Created in 2020-12-10 10:04
 * @modified By
 */
@AllArgsConstructor
@Getter
public enum CodeEnum {

    SUCCESS("0000", "操作成功！"),
    FAIL("1000", "操作失败！"),
    RATE_LIMIT_Failure("1001", "限流异常！")
    ;

    /**
     * 异常相关编码 
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;
}
