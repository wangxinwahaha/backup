/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisinsight.framework.uuv.common.interceptor.UserInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * description web配置
 *
 * @author long [KF.longjiang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
        super.addInterceptors(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getMappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        //设置日期格式ISO-8601
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        objectMapper.setDateFormat(smt);
        //设置中文编码格式
//        List<MediaType> list = new ArrayList<MediaType>();
//        list.add(MediaType.APPLICATION_JSON);
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);

//        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategyBase());
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new WebHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }
}