package com.tjl.base.gentrator.config;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 * @author tanjianglong
 * @Type TjlInjectionConfig
 * @date 2020/1/2 14:30
 * @Version 1.0
 */
public class TjlInjectionConfig extends InjectionConfig {
    //添加 配置的全局变量
    @Override
    public void initMap() {

    }

    //自定义输出文件*********可以生成 除 mapper service controller 以外的文件  根据模版
    public List<FileOutConfig> initFileOutConfig() {
        List<FileOutConfig> fileOutConfigs = new ArrayList();
        return fileOutConfigs;
    }
}
