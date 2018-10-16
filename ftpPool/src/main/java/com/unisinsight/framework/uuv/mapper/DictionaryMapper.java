/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.DictionaryDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * 字典mapper层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 9:53
 * @since 1.0
 */
public interface DictionaryMapper extends Mapper<DictionaryDO> {
    /**
     * 插入应用，可返回id
     *
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(DictionaryDO record);

    /**
     * 插入应用，可返回id
     *
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSelective(DictionaryDO record);
}