package com.bhh.imooc.alllearning996.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author bhh
 * @description 校验工具类
 * @date Created in 2020-12-10 15:43
 * @modified By
 */
public class ValidatorUtils {
    /**
     * 校验器
     */
    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    /**
     * 参数校验
     * @param object
     * @param groups
     * @param <T>
     */
    public static <T> void validation(T object, Class ...groups){
        Set<ConstraintViolation<T>> validate =
                validator.validate(object, groups);

        //如果检验数据生成的对象为空时
        if(validate == null || validate.size() == 0){
            return;
        }

        //对象非空，即有错误生成
        StringBuilder exceptionMessage = new StringBuilder();
        validate.forEach(constraintViolation -> {
            exceptionMessage.append(constraintViolation.getMessage());
        });
        throw new RuntimeException(exceptionMessage.toString());
    }
}
