package com.bhh.imooc.alllearning996.exception;

import com.bhh.imooc.alllearning996.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bhh
 * @description 全局异常捕获器
 * @date Created in 2020-12-10 18:39
 * @modified By
 */
//对含有@Controller和@RestController的注解会有拦截异常的作用
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 拦截捕获业务类异常，进行统一回复
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessExceptionHandler(BusinessException e){
        log.error("捕捉到业务类异常:", e);

        return ResponseResult.fail(CodeEnum.FAIL.getCode(), e.getMessage());
    }

    /**
     * 拦截捕捉运行时异常，进行统一回复处理
     * @param e
     * @return
     */
    @ResponseBody//标识返回json到前端，否则不会直接到前端
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExceptionHandler(RuntimeException e){

        log.error("runtimeException异常捕捉成功");
        return ResponseResult.fail(
                CodeEnum.FAIL.getCode(),
                e.getMessage());
    }

    /**
     * 当有非runtimeException的其他报错生成时，由此方法来处理
     * @param throwable
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult exceptionHandler(Throwable throwable){

        log.error("throw异常捕捉成功");
        return ResponseResult.fail(
                CodeEnum.FAIL.getCode(),
                throwable.getMessage());
    }

}
