/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.StringUtils;
import com.unisinsight.framework.uuv.dto.request.PrivilegeReqDTO;
import com.unisinsight.framework.uuv.dto.response.PrivilegeResDTO;
import com.unisinsight.framework.uuv.service.PrivilegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 权限Controller层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/applications/{app_id}/privileges")
public class PrivilegeController {
    @Resource
    private PrivilegeService privilegeService;

    /**
     * 新增权限
     * @param privilegeReqDTO
     * @param appId
     * @return
     */
    @PostMapping()
    public PrivilegeResDTO add(@RequestBody @Valid PrivilegeReqDTO privilegeReqDTO,
                               @PathVariable("app_id") Integer appId) {
        return privilegeService.save(privilegeReqDTO,appId);
    }

    /**
     * 根据id删除权限
     * @param appId
     * @return
     */
    @DeleteMapping("/{privilege_id}")
    public void delete(@PathVariable(value = "app_id") Integer appId,
                          @PathVariable(value = "privilege_id") String privilegeIds) {
        if (StringUtils.isEmpty(privilegeIds)) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }

        privilegeService.deleteById(appId, StringUtils.stringToList(privilegeIds));
    }

    /**
     * 修改权限
     * @param privilegeReqDTO
     * @param appId
     * @param privilegeId
     * @return
     */
    @PutMapping("/{privilege_id}")
    public PrivilegeResDTO update(@RequestBody @Valid PrivilegeReqDTO privilegeReqDTO,
                          @PathVariable("app_id") Integer appId,
                          @PathVariable("privilege_id") Integer privilegeId) {
        return privilegeService.update(privilegeReqDTO,appId,privilegeId);
    }
}
