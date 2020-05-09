
package com.tjl.base.common.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 功能描述
 * @author tanjianglong
 * @Type DemoData
 * @date 2020/5/9 17:52
 * @Version 1.0
 */
@Data
public class DemoData {

    /**
     * 第一列的数据
     */
    @ExcelProperty(index = 0)
    private String column1;
    /**
     * 第二列的数据
     */
    @ExcelProperty(index = 1)
    private String column2;

    @ExcelProperty(index = 2)
    private String column3;

    @ExcelProperty(index = 3)
    private String column4;

    @ExcelProperty(index = 4)
    private String column5;
}
