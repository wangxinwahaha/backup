/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.dto.request.UpdatePwdDTO;

import com.unisinsight.framework.uuv.model.ApplicationDO;
import com.unisinsight.framework.uuv.model.UserDetailInfoDO;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import com.unisinsight.framework.uuv.model.UserInfoListDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:01
 * @since 1.0
 */
public interface UserInfoMapper extends Mapper<UserInfoDO> {

    /**
     * 获取用户列表
      * @param params
     * @param orgId
     * @param positionIds
     * @param roleIds
     * @param appIds
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @return
     */
    List<UserInfoListDO> list(@Param("params") Map<String, Object> params
            , @Param("orgId") String orgId
            , @Param("positionIds") List<Integer> positionIds
            , @Param("roleIds") List<Integer> roleIds
            , @Param("appIds") List<Integer> appIds
            , @Param("pageIndex") Integer pageIndex
            , @Param("pageSize") Integer pageSize
            , @Param("orderFiled") String orderFiled
            , @Param("orderRule") String orderRule);

    /**
     * 获取用户总数
     * @param params
     * @param orgId
     * @param positionIds
     * @param roleIds
     * @param appIds
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @return
     */
    Integer count(@Param("params") Map<String, Object> params
            , @Param("orgId") String orgId
            , @Param("positionIds") List<Integer> positionIds
            , @Param("roleIds") List<Integer> roleIds
            , @Param("appIds") List<Integer> appIds
            , @Param("pageIndex") Integer pageIndex
            , @Param("pageSize") Integer pageSize
            , @Param("orderFiled") String orderFiled
            , @Param("orderRule") String orderRule);

    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return
     */
    UserDetailInfoDO selectUserById(Integer userId);

    /**
     * 通过用户code获取用户信息
     * @param userCode
     * @return
     */
    UserDetailInfoDO selectUserByUserCode(String userCode);

    /**
     * 插入用户，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    int insert(UserInfoDO record);

    /**
     * 插入用户，若属性值为null，则使用数据库默认值，可返回id
     * @param record
     * @return
     */
    @Override
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    int insertSelective(UserInfoDO record);
}