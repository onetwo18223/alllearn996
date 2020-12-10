package com.bhh.imooc.alllearning996.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bhh
 * @description User实体类
 * @date Created in 2020-12-09 9:28
 * @modified By
 */
@Data
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = -4681320124616004426L;

    /** 用户主信息 **/
    /**
     * 用户名称
     */
    private String username;

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

    /** 系统主信息 **/
    /**
     * 数据库id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 数据库创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;

    /**
     * 数据修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modified;

    /**
     * 数据创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;
    
    /**
     * 数据最后修改者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;

    /**
     * 数据状态（逻辑删除字段，0：正常，1：逻辑删除）
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    /**
     * 数据版本号
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;
}
