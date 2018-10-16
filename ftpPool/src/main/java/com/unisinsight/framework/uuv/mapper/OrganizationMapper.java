/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.OrganizationDO;
import com.unisinsight.framework.uuv.model.OrganizationExtraDO;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

/**
 * 组织mapper层
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 9:55
 * @since 1.0
 */
public interface OrganizationMapper extends Mapper<OrganizationDO> {
    /**
     * 获取所有组织信息
     * @return
     */
    List<OrganizationExtraDO> getAllOrgInfo();

    /**
     * 插入组织，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "org_id", keyProperty = "orgId")
    int insert(OrganizationDO record);

    /**
     * 插入组织，若属性值为null，则使用数据库默认值，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "org_id", keyProperty = "orgId")
    int insertSelective(OrganizationDO record);
}