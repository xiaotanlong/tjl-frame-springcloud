package com.tjl.base.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tjl
 * @date 2019/11/14 14:34
 * @Description: 使用 com.alibaba.easyexcel 开源工具导入导出Excel
 * https://alibaba-easyexcel.github.io/quickstart/read.html
 */
public class EasyExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * 功能描述:简单的写入
     * @param fileName 文件名
     * @param dataList 数据列表 T 要求有com.alibaba.excel.annotation 相关注解
     * @param sheetName 片名称
     * @param c 数据模板类
     * @return void
     * @date 2019/11/14
     */
    public static <T> void simpleWrite(String fileName, List<T> dataList, String sheetName, Class c) {
        EasyExcel.write(fileName, c).sheet(sheetName).doWrite(dataList);

    }

    /**
     * 功能描述: 异步读取excel数据
     * @param readPath     文件路径
     * @param c 数据模版类
     * @return void
     */
    public static <T> List<T> simpleReadSync(String readPath, Class c) {
        List<T> list = new ArrayList<>();
        //同步读取
        List<Object> readList = EasyExcel.read(readPath, c, new DemoDataListener()).sheet().doReadSync();
        for (Object obj : readList) {
            list.add((T) obj);
        }
        return list;
    }

    /**
     * 功能描述: 异步读取excel数据
     * @param readPath     文件路径
     * @param readListener 异步读取监听器
     * @return void
     */
    public static void simpleRead(String readPath, ReadListener readListener) {
        //异步读取
        EasyExcel.read(readPath, readListener).sheet().doRead();
    }

    public static void main(String[] args) {
        List<DemoData> list = EasyExcelUtil.simpleReadSync("d:\\aaa.xlsx", DemoData.class);
        for (DemoData mode : list) {
            String s = mode.getColumn4();
            //String s = "式XIII字符格式Ⅰ、Ⅱ、Ⅲ、Ⅳ、Ⅴ、Ⅵ、Ⅶ、Ⅷ、Ⅸ、Ⅹ、Ⅺ、Ⅻ、罗马数字格式00000正则表达式语法规则3121书写,ⅷ小写";
            String reg_charset = "[\u4E00-\u9FA5]|[a-zA-Z]|[0-9]|[\u2160-\u217F]";
            StringBuffer strs = new StringBuffer();
            Pattern p = Pattern.compile(reg_charset);
            Matcher m = p.matcher(s);
            while (m.find()) {
                strs.append(m.group());
            }
            mode.setColumn5(strs.toString());
        }
        EasyExcelUtil.simpleWrite("d:\\aaa3.xlsx", list, "sheet1", DemoData.class);

        EasyExcelUtil.simpleRead("d:\\aaa.xlsx", new DemoDataListener());
    }
}
