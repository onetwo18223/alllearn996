package com.bhh.imooc.alllearning996.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bhh
 * @description User类VO实体
 * @date Created in 2020-12-09 10:28
 * @modified By
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -4864727427494403297L;

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

}
