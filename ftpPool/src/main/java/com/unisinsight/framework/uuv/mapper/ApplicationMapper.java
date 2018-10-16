/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.ApplicationDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

/**
 * 应用mapper层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 9:52
 * @since 1.0
 */
public interface ApplicationMapper extends Mapper<ApplicationDO> {

    /**
     * 获取用户可访问应用列表
     *
     * @param userId
     * @return
     */
    List<ApplicationDO> getUserApplications(Integer userId);

    /**
     * 获取用户可访问应用列表
     *
     * @param userCode
     * @return
     */
    List<ApplicationDO> getUserAppByCode(String userCode);

    /**
     * 插入应用，可返回id
     *
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "app_id", keyProperty = "appId")
    int insert(ApplicationDO record);

    /**
     * 插入应用，可返回id
     *
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "app_id", keyProperty = "appId")
    int insertSelective(ApplicationDO record);

}