package com.bhh.imooc.alllearning996.domain.dto;

import lombok.Data;

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
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 数据版本号
     */
    private Long version;

}
