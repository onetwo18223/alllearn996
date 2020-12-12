package com.bhh.imooc.alllearning996.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author bhh
 * @description LocalDateTime String 转换器
 * @date Created in 2020-12-11 14:50
 * @modified By
 */
@Slf4j

public class LocalDateTimeStringConverter implements Converter<LocalDateTime> {

    /**
     * Java对象
     *
     * @return
     */
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    /**
     * Excel对象
     *
     * @return
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 导入
     *
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public LocalDateTime convertToJavaData(
            CellData cellData,
            ExcelContentProperty excelContentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    /**
     * 导出
     *
     * @param localDateTime
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(
            LocalDateTime localDateTime,
            ExcelContentProperty excelContentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {

        //未指定格式化形式,默认
        if (excelContentProperty == null ||
                excelContentProperty.getDateTimeFormatProperty() == null) {
            return new CellData(DateTimeFormatter
                    .ISO_LOCAL_DATE_TIME
                    .format(localDateTime));
        } else {
            //指定格式化形式

            return new CellData(DateTimeFormatter.ofPattern(
                    excelContentProperty
                            .getDateTimeFormatProperty()
                            .getFormat()).format(localDateTime));
        }
    }
}
