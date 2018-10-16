/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.config;

import com.unisinsight.framework.uuv.base.ListParams;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * description 分页配置
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class WebHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String DEFAULT_PAGE_INDEX = "$pageindex";
    private static final String DEFAULT_PAGE_SIZE = "$pagesize";
    private static final String DEFAULT_COUNT = "$count";
    private static final String DEFAULT_FILTER = "$filter";
    private static final String DEFAULT_ORDER_BY = "$orderby";


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return ListParams.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest request, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class entityClass = getEntityClazz(parameter);
        if (entityClass == null) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }
        int pageIndex = NumberUtils.toInt(request.getParameter(DEFAULT_PAGE_INDEX), 0);
        int pageSize = NumberUtils.toInt(request.getParameter(DEFAULT_PAGE_SIZE), 20);
        boolean count = BooleanUtils.toBoolean(request.getParameter(DEFAULT_COUNT));
        String[] filter = request.getParameterValues(DEFAULT_FILTER);
        String orderBy = request.getParameter(DEFAULT_ORDER_BY);

        return listParam(entityClass, pageIndex, pageSize, count, filter, orderBy);
    }

    private <T> Object listParam(Class<T> entityClass, int pageIndex, int pageSize, boolean count, String[] filter, String orderBy) {
        ListParams<T> listParam = new ListParams<>(entityClass);
        listParam.setPageIndex(pageIndex);
        listParam.setPageSize(pageSize);
        listParam.setCount(count);
        listParam.parseOrderBy(orderBy);
        listParam.parseFilter(filter);
        return listParam;
    }


    private static Class getEntityClazz(MethodParameter parameter) {
        Type type = parameter.getGenericParameterType();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) parameter.getGenericParameterType();
            Type[] entity = parameterizedType.getActualTypeArguments();
            if (entity.length > 0) {
                return (Class) entity[0];
            }
        }
        return null;
    }
}