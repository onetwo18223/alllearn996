package com.bhh.imooc.alllearning996.domain.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bhh
 * @description 分页通过返回结果
 * @date Created in 2020-12-09 11:25
 * @modified By
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 7626056702522814376L;

    /**
     * 所处页数
     */
    private Integer pageNo;
    
    /**
     * 每页数据量 
     */
    private Integer pageSize;
    
    /**
     * 数据总量 
     */
    private Integer total;
    
    /**
     * 总页数
     */
    private Integer pageNumber;

    /**
     * 动态数据
     */
    private T data;
}
