/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.utils;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description 实体转换类
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class BeanConvert {
    public static <T> List<T> convertList(Object source, Class<T> c) {

        if (!(source instanceof List)){
            throw new RuntimeException("source不是list不能进行转换");
        }

        List<Object> list = (List<Object>) source;

        return  list.stream().map(model -> convert(model, c)).collect(Collectors.toList());
    }

    public static <T> T convert(Object source,  Class<T> c) {
        T target = null;
        try {
            target = c.newInstance();
            BeanUtils.copyProperties(source,target);
        } catch (InstantiationException e) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "实例化异常");
        } catch (IllegalAccessException e) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "非法访问异常");
        }
        return target;
    }

}
