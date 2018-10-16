/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * description Mybatis & Mapper & PageHelper 配置
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@Configuration
public class MybatisConfigurer {

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.unisinsight.framework.uuv.model");

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));
        return factory.getObject();
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("com.unisinsight.framework.uuv.mapper");

        //配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.unisinsight.framework.uuv.base.Mapper");

        //insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("notEmpty", "false");properties.setProperty("IDENTITY", "POSTGRES");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}

