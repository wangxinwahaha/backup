/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.base;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public interface Mapper<T>
        extends
        BaseMapper<T>,
        ConditionMapper<T>,
      //  IdsMapper<T>,
        InsertListMapper<T> {
}
