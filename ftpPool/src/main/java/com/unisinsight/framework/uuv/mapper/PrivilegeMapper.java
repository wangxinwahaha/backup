/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.PrivilegeDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

/**
 * 权限mapper层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 9:58
 * @since 1.0
 */
public interface PrivilegeMapper extends Mapper<PrivilegeDO> {

    /**
     * 查找角色权限
     * @param appId
     * @param roleId
     * @param search
     * @return
     */
    List<PrivilegeDO> selectAppRolePri(@Param("appId") Integer appId,
                                       @Param("roleId") Integer roleId,
                                       @Param("search") Object search);

    /**
     * 获取角色未拥有的权限
     * @param appId
     * @param roleId
     * @param search
     * @return
     */
    List<PrivilegeDO> selectAppRoleNotPri(@Param("appId") Integer appId,
                                          @Param("roleId") Integer roleId,
                                          @Param("search") Object search);

    /**
     * 通过appId获取权限
     * @param appId
     * @param dataType
     * @param search
     * @return
     */
    List<PrivilegeDO> getPrivilegeByAppId(@Param("app_id") Integer appId,
                                          @Param("dataType") Integer dataType,
                                          @Param("search") Object search);

    /**
     * 插入权限，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "privilege_id", keyProperty = "privilegeId")
    int insert(PrivilegeDO record);

    /**
     * 插入权限，若属性值为null，则使用数据库默认值，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "privilege_id", keyProperty = "privilegeId")
    int insertSelective(PrivilegeDO record);

}