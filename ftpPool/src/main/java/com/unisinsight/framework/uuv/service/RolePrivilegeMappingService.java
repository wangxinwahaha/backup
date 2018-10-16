/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.unisinsight.framework.uuv.dto.response.RolePrivilegeResDTO;

import java.util.List;
import java.util.Set;

/**
 * description 角色权限关系接口
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:10
 * @since 1.0
 */
public interface RolePrivilegeMappingService {
    /**
     * 角色添加权限
     * @param appId
     * @param roleId
     * @param privilegeIds
     * @return
     */
    List<RolePrivilegeResDTO> addPrivileges(Integer appId, Integer roleId, Set<Integer> privilegeIds);

    /**
     * 角色删除部分权限
     * @param appId
     * @param roleId
     * @param list
     * @return
     */
    void deleteRolePrivilege(Integer appId, Integer roleId, List<Integer> list);
}
