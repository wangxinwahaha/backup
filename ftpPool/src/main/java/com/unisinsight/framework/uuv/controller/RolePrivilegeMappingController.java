/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.dto.response.RolePrivilegeResDTO;
import com.unisinsight.framework.uuv.service.RolePrivilegeMappingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色权限Controller层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/applications/{appId}/roles")
public class RolePrivilegeMappingController {

    private final static String PRIVILEGES = "privileges";

    @Resource
    private RolePrivilegeMappingService rolePrivilegeMappingService;

    /**
     * 角色添加权限（批量添加）
     *
     * @param roleId
     * @param privileges
     * @return
     */
    @PostMapping("/{roleId}/privileges")
    public  List<RolePrivilegeResDTO> addPrivileges(@PathVariable("appId") Integer appId,
                                                    @PathVariable("roleId") Integer roleId,
                                                    @RequestBody Map<String, Object> privileges) {


        if (privileges.get(PRIVILEGES) == null || !(privileges.get(PRIVILEGES) instanceof List)) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }
        List<Integer> priList = (List<Integer>) privileges.get("privileges");
        //参数去重
        Set<Integer> set = new HashSet<>(priList.size());
        set.addAll(priList);
        return rolePrivilegeMappingService.addPrivileges(appId, roleId, set);

    }

    /**
     * 角色删除部分权限(批量删除)
     *
     * @param roleId
     * @param list   权限ID列表
     */
    @DeleteMapping("/{roleId}/privileges/{privilegeId}")
    public void deletePrivileges(@PathVariable("appId") Integer appId,
                                 @PathVariable("roleId") Integer roleId,
                                 @PathVariable("privilegeId") List<Integer> list) {
        rolePrivilegeMappingService.deleteRolePrivilege(appId, roleId, list);
    }
}
