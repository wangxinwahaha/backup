/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.StringUtils;
import com.unisinsight.framework.uuv.dto.request.RoleReqDTO;
import com.unisinsight.framework.uuv.dto.response.RoleResDTO;
import com.unisinsight.framework.uuv.dto.response.UserRoleMappingResDTO;
import com.unisinsight.framework.uuv.model.RoleDO;
import com.unisinsight.framework.uuv.model.UserRoleMappingDO;
import com.unisinsight.framework.uuv.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

/**
 * 角色Controller层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/applications/{appId}/roles")
public class RoleController {

    private final static String USER_IDS = "user_ids";

    @Resource
    private RoleService roleService;

    /**
     * 添加角色接口
     *
     * @param roleReqDTO
     * @param appId
     * @return
     */
    @PostMapping()
    public RoleResDTO add(@RequestBody @Valid RoleReqDTO roleReqDTO, @PathVariable("appId") Integer appId) {
        //判断角色code是否为空
        if (StringUtils.isEmpty(roleReqDTO.getRoleCode())) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "角色code为空");
        }
        return roleService.save(roleReqDTO, appId);
    }

    /**
     * 删除角色接口
     *
     * @param appId
     * @param roleId
     * @return
     */
    @DeleteMapping("/{roleId}")
    public void delete(@PathVariable("appId") Integer appId, @PathVariable("roleId") String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }
        roleService.deleteById(appId, StringUtils.stringToList(roleId));
    }

    /**
     * 修改角色接口
     *
     * @param appId
     * @param roleId
     * @param roleReqDTO
     * @return
     */
    @PutMapping("/{roleId}")
    public RoleResDTO update(@PathVariable("appId") Integer appId,
                             @PathVariable("roleId") Integer roleId,
                             @RequestBody @Valid RoleReqDTO roleReqDTO) {
        return roleService.update(roleReqDTO, appId, roleId);
    }

    /**
     * 获取角色详情接口
     *
     * @param appId
     * @return 用户详情list
     */
    @GetMapping()
    public List<RoleResDTO> detail(@PathVariable("appId") Integer appId) {
        return roleService.findAll(appId);
    }

    /**
     * 给角色新增用户（支持批量新增）
     *
     * @param appId
     * @param roleId
     * @param userId
     * @return
     */
    @PostMapping("{roleId}/users")
    public List<UserRoleMappingResDTO> saveNewUserToRole(@PathVariable("appId") Integer appId,
                                                         @PathVariable("roleId") Integer roleId,
                                                         @RequestBody Map<String, Object> userId) {

        if (userId.get(USER_IDS) == null && !(userId.get(USER_IDS) instanceof List)) {
            throw new RuntimeException("param error");
        }
        List<Integer> userIds = (List<Integer>) userId.get("user_ids");
        return  roleService.saveNewUserToRole(userIds, appId, roleId);
    }

    /**
     * 角色删除用户（支持批量删除）
     *
     * @param userIds
     * @param appId
     * @param roleId
     * @return
     */
    @DeleteMapping("/{roleId}/users/{user_id}")
    public void deleteUserFromRole(@PathVariable(value = "user_id") String userIds,
                                      @PathVariable("appId") Integer appId,
                                      @PathVariable("roleId") Integer roleId) {
        if (StringUtils.isEmpty(userIds)) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }

        Set<Integer> ids = new HashSet<>(StringUtils.stringToList(userIds));

        roleService.deleteUserFromRole(ids, appId, roleId);

    }
}
