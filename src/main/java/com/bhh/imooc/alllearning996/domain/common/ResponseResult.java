package com.bhh.imooc.alllearning996.domain.common;

import com.bhh.imooc.alllearning996.exception.CodeEnum;
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
     * @param result 成功结果
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result){
        ResponseResult<T> responseResult = new ResponseResult<T>();

        responseResult.setJudge(Boolean.TRUE);
        responseResult.setResult(result);

        return responseResult;
    }

    /**
     * 成功
     * @param code 返回的状态编码
     * @param result 结果
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(String code, T result){
        ResponseResult<T> responseResult = new ResponseResult<T>();

        responseResult.setJudge(Boolean.TRUE);
        responseResult.setResult(result);
        responseResult.setCode(code);

        return responseResult;
    }

    /**
     * 成功
     * @param code 成功编码
     * @param message 成功信息
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(String code, String message,
                                                T result){
        ResponseResult<T> responseResult = new ResponseResult<T>();

        responseResult.setJudge(Boolean.TRUE);
        responseResult.setResult(result);
        responseResult.setCode(code);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 成功
     * @param codeEnum 状态码
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(CodeEnum codeEnum, T result){
        ResponseResult<T> responseResult = new ResponseResult<T>();

        responseResult.setJudge(Boolean.TRUE);
        responseResult.setResult(result);
        responseResult.setCode(codeEnum.getCode());
        responseResult.setMessage(codeEnum.getMessage());

        return responseResult;
    }

    /**
     * 响应失败信息
     * @param code 失败编码
     * @param message 失败信息
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> fail(String code, String message){
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setJudge(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 响应失败信息
     * @param codeEnum 异常枚举类
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> fail(CodeEnum codeEnum){
        return fail(codeEnum.getCode(), codeEnum.getMessage());
    }
}
