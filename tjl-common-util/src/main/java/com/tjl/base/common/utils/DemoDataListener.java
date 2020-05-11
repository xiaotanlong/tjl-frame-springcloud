package com.tjl.base.common.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述 这个类不能被spring管理  因为有泛型  需要在使用是 new出来  使用spring组件时 需要使用构造函数传递或者 spring容器工厂获取
 * @author tjl
 * @date 2020/5/11 14:54
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);

    //这个每一条数据解析都会来调用
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        LOGGER.info("=======invoke======data:{}",data);
    }

    //所有数据解析完成了 都会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info("=======doAfterAllAnalysed======");
    }
}
