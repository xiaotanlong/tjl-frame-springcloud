package com.tjl.base.dao.mybatisplus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 功能描述 配置mybatis plus
 * @author tjl
 * @Type MyBatisPlusConfig
 * @date 2019/12/12 14:33
 * @Version 1.0
 */
@EnableTransactionManagement
@Configuration
@ConditionalOnMissingBean({PaginationInterceptor.class, ConfigurationCustomizer.class})
public class MyBatisPlusConfig {
    public MyBatisPlusConfig() {
        System.out.println("====================================MyBatisPlusConfig init==================");
    }

    /***
     * 功能描述: 注册分页插件
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @date 2019/12/12
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    /**
     * 功能描述:性能分析拦截器，用于输出每条 SQL 语句及其执行时间
     * 参数：maxTime SQL 执行最大时长，超过自动停止运行，有助于发现问题。
     * 参数：format SQL SQL是否格式化，默认false。
     * @return com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
     * @date 2019/12/12 
     */
    @Bean
    @Profile({"test","dev"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * 功能描述:使用MybatisPlus的MybatisMapWrapperFactory 替换 mybatis的 DefaultObjectWrapperFactory
     * @return com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer
     * @date 2019/12/13 
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new MyBatisPlusConfig.MybatisPlusCustomizers();
    }

    class MybatisPlusCustomizers implements ConfigurationCustomizer {
        MybatisPlusCustomizers() {
        }
        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        }
    }
}
