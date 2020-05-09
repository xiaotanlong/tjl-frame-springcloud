package com.tjl.base.common.utils;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jianglong.Tan
 * @date 2019/11/14 14:34
 * @Description: 使用 com.alibaba.easyexcel 开源工具导入导出Excel
 * api
 * https://alibaba-easyexcel.github.io/quickstart/read.html
 */
public class EasyExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * 功能描述:简单的写入
     * @param fileName 文件名
     * @param dataList 数据列表 T 要求有com.alibaba.excel.annotation 相关注解
     * @return void
     * @date 2019/11/14
     */
    public static void simpleWrite(String fileName, List dataList, String sheetName) {
        EasyExcel.write(fileName, dataList.get(0).getClass()).sheet(sheetName).doWrite(dataList);

    }

    public static void simpleRead() {
        String readPath = "D:\\aaaa.xlsx";
        try {

            //异步读取
            EasyExcel.read(readPath, DemoData.class, null).sheet().doRead();
            //同步读取
            List<Object> readList = EasyExcel.read(readPath, DemoData.class, null).sheet().doReadSync();
            List<DemoData> list = new ArrayList<DemoData>();
            for (Object obj : readList) {
                list.add((DemoData)obj);
            }
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
            simpleWrite("D:\\aaaa2.xlsx", list, "sheet1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        simpleRead();
    }
}
