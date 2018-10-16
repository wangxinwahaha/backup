/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.unisinsight.framework.uuv.common.utils.StringUtils;

/**
 * description 下划线驼峰转换
 *
 * @author long [KF.longjiang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class PropertyNamingStrategyBase extends PropertyNamingStrategy {

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return StringUtils.underscoreName(defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return StringUtils.underscoreName(defaultName);
    }


}