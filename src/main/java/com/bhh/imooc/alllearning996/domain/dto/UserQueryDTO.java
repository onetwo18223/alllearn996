package com.bhh.imooc.alllearning996.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author bhh
 * @description 用户查询条件DTO实体
 * @date Created in 2020-12-09 11:16
 * @modified By
 */
@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = 6040717402815393695L;

    /**
     * 用户名称
     */
    //@NotBlank(message = "查询信息用户名不能为空")
    private String username;
}
