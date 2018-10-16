/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description json转换
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class BeanUtils {

    public static <T> T toBean(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper.readValue(json, clazz);
    }

    public static <T> List<T> toList(String json,  Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        return mapper.readValue(json, type);
    }
}
