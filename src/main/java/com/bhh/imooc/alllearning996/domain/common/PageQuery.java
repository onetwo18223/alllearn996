package com.bhh.imooc.alllearning996.domain.common;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author bhh
 * @description 通用分页查询对象
 * @date Created in 2020-12-09 14:45
 * @modified By
 */
@Data
public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = 2208763428717451099L;

    /**
     * 第几页
     */
    @NotNull(message = "页数不能为空")
    @Min(value = 1, message = "页数必须是正数")
    private Integer pageNo = 1;

    /**
     * 每一页数据量
     */
    @NotNull(message = "每页数据量不能为空")
    @Max(value= 100, message = "每页数据量最大不能超过100")
    private Integer pageSize = 20;

    /**
     * 动态查询条件
     */
    //级联验证
    @Valid
    @NotNull(message = "动态查询条件不能为空")
    private T query;
}
