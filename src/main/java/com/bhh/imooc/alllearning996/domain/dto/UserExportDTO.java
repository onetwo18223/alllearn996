package com.bhh.imooc.alllearning996.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.bhh.imooc.alllearning996.util.LocalDateTimeStringConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bhh
 * @description 用于导出UserDO到Excel
 * @date Created in 2020-12-11 13:53
 * @modified By
 */


/**
 * @ExcelProperty中的value是列表，
 * 生成的excel列顺序和名称是按照代码中@ExcelProperty顺序和其中的value表示的
 */
@Data
public class UserExportDTO implements Serializable {

    private static final long serialVersionUID = -7121054335578209958L;

    /**
     * 用户名称
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 用户email
     */
    @ExcelProperty(value = "用户邮箱")
    private String email;

    /**
     * 用户年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 用户电话
     */
    @ExcelProperty(value = "电话")
    private String phone;

    /**
     * 版本号
     */
    @ExcelProperty(value = "数据版本号")
    private Long version;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间",
            converter = LocalDateTimeStringConverter.class)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒")
    private LocalDateTime created;
}
