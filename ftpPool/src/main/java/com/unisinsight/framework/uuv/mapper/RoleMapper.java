/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.RoleDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;


import java.util.List;

/**
 * 角色mapper层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:59
 * @since 1.0
 */
public interface RoleMapper extends Mapper<RoleDO> {
    /**
     * 插入用户，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
    int insert(RoleDO record);

    /**
     * 插入用户，若属性值为null，则使用数据库默认值，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
    int insertSelective(RoleDO record);
}
