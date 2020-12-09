package com.bhh.imooc.alllearning996.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bhh
 * @description 通用返回结果模型
 * @date Created in 2020-12-09 10:46
 * @modified By
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -96386682161274675L;

    /**
     * 是否成功
     */
    private Boolean judge;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 编码信息
     */
    private String code;

    /**
     * 返回结果
     */
    private T result;

    /**
     * 成功
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result){
        ResponseResult<T> responseResult = new ResponseResult<T>();

        responseResult.setJudge(true);
        responseResult.setResult(result);
        return responseResult;
    }
}
