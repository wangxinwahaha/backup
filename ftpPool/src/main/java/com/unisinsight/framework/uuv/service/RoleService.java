/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.dto.request.RoleReqDTO;
import com.unisinsight.framework.uuv.dto.response.RoleResDTO;
import com.unisinsight.framework.uuv.dto.response.UserRoleMappingResDTO;
import com.unisinsight.framework.uuv.model.RoleDO;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import com.unisinsight.framework.uuv.model.UserInfoListDO;
import com.unisinsight.framework.uuv.model.UserRoleMappingDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description 角色接口
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:10
 * @since 1.0
 */
public interface RoleService  {

    /**
     * 新增
     * @param reqDTO
     * @param appId
     * @return
     */
    RoleResDTO save(RoleReqDTO reqDTO, Integer appId);


    /**
     * 删除
     * @param appId
     * @param roleIds
     * @return
     */
    void deleteById(Integer appId, List<Integer> roleIds);

    /**
     * 更新
     * @param updateDTO
     * @param appId
     * @param roleId
     * @return
     */
    RoleResDTO update(RoleReqDTO updateDTO, Integer appId, Integer roleId);

    /**
     * 获取所有
     * @param appId
     * @return
     */
    List<RoleResDTO> findAll(Integer appId);

    /**
     * 批量为角色添加用户
     * @param userIds
     * @param appId
     * @param roleId
     * @return
     */
    List<UserRoleMappingResDTO> saveNewUserToRole(List<Integer> userIds, Integer appId, Integer roleId);

    /**
     * 为角色删除用户
     * @param ids
     * @param appId
     * @param roleId
     * @return
     */
    void deleteUserFromRole(Set<Integer> ids, Integer appId, Integer roleId);

}
