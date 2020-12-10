package com.bhh.imooc.alllearning996.domain.dto;

import com.bhh.imooc.alllearning996.util.InsertValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author bhh
 * @description User类DTO实体
 * @date Created in 2020-12-09 9:38
 * @modified By
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -8132294393067466686L;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空",
            groups = {InsertValidationGroup.class})
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户名不能为空",
            groups = {InsertValidationGroup.class})
//    密码长度分开验证
    @Length(min = 6, message = "密码长度不能小于6")
    @Length(max = 18, message = "密码长度不能大于18")
    private String password;

    /**
     * 用户邮箱
     */
    //非空才会验证邮箱格式
    @Email
    @NotBlank(message = "邮箱名不能为空",
            groups = {InsertValidationGroup.class})
    private String email;

    /**
     * 用户年龄
     */
    @Min(value = 10, message = "年龄不能小于10")
    @Max(value = 200, message = "年龄不能大于200")
    @NotNull(message = "年龄不能为空",
            groups = {InsertValidationGroup.class})
    private Integer age;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空", groups = {InsertValidationGroup.class})

    private String phone;

    /**
     * 数据版本号
     */
    private Long version;

}
