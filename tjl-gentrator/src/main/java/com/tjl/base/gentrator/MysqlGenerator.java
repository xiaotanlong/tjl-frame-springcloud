package com.tjl.base.gentrator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述
 * @author tanjianglong
 * @Type MysqlGenerator
 * @date 2019/12/12 15:52
 * @Version 1.0
 */
public class MysqlGenerator {
    private static Logger logger = LoggerFactory.getLogger(MysqlGenerator.class);
    // 自定义基类
    private final static String SuperEntity = "com.baomidou.mybatisplus.samples.generator.common.BaseEntity";// 所有实体的基类(全类名)
    private final static String SuperController = "com.baomidou.mybatisplus.samples.generator.common.BaseController";// 所有控制器的基类(全类名)
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //输出文件路径
        gc.setOutputDir("E:\\develop\\tjl");
        //作者
        gc.setAuthor("tanjianglong");
        //是否打开输出目录 默认 true
        gc.setOpen(false);
        //开启 BaseResultMap 默认 false
        gc.setBaseResultMap(true);
        //baseColumnList 默认 false
        gc.setBaseColumnList(true);
        gc.setDateType(DateType.TIME_PACK);
        /**
         * 指定生成的主键的ID类型
         * AUTO: 数据库ID自增
         * NONE: 该类型为未设置主键类型
         * INPUT: 用户输入ID,该类型可以通过自己注册自动填充插件进行填充
         * ID_WORKER: 全局唯一ID,只有当插入对象ID 为空，才自动填充
         * UUID: 全局唯一ID ,只有当插入对象ID 为空，才自动填充
         * ID_WORKER_STR: 字符串全局唯一ID ,只有当插入对象ID 为空，才自动填充
         */
        gc.setIdType(IdType.AUTO);
        //%s 为占位符
        gc.setEntityName("%sDO");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/bigdb_dev?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        mpg.setDataSource(dataSourceConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setTablePrefix("s_");
        strategyConfig.setInclude("s_business_positive");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass(SuperEntity);
        strategyConfig.setEntityLombokModel(true);//【实体】是否为lombok模型
        strategyConfig.setSuperControllerClass(SuperController);
//        strategy.setInclude(scanner("表名"));
        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategyConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(injectionConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setModuleName("com.tjl.base");
        pc.setParent("tjl");
        mpg.setPackageInfo(pc);


        //指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
        // 注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity2.java");
        mpg.setTemplate(templateConfig);


        mpg.execute();
    }
}
