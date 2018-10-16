/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.base.PageParam;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.dto.request.ApplicationReqDTO;
import com.unisinsight.framework.uuv.dto.response.ApplicationResDTO;
import com.unisinsight.framework.uuv.dto.response.PrivilegeResDTO;
import com.unisinsight.framework.uuv.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 应用Controller层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/applications")
public class ApplicationController {
    @Resource
    private ApplicationService applicationService;

    /**
     * 添加应用
     *
     * @param applicationReqDTO
     * @return 成功返回0
     * 失败返回异常
     */
    @PostMapping
    public ApplicationResDTO addApp(@RequestBody @Valid ApplicationReqDTO applicationReqDTO) {
        return applicationService.save(applicationReqDTO);
    }

    /**
     * 删除应用
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteApp(@PathVariable Integer id) {
        applicationService.deleteById(id);
    }

    /**
     * 修改应用
     *
     * @param applicationReqDTO
     * @param appId
     * @return
     */
    @PutMapping("/{appId}")
    public ApplicationResDTO updateApp(@RequestBody ApplicationReqDTO applicationReqDTO,
                                       @PathVariable("appId") Integer appId) {
        return applicationService.update(applicationReqDTO, appId);
    }

    /**
     * 获取应用详情
     *
     * @param appId
     * @return
     */
    @GetMapping("/{appId}")
    public ApplicationResDTO getAppDetail(@PathVariable Integer appId) {
        return applicationService.getAppById(appId);
    }

    /**
     * 获取应用列表
     *
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @param params
     * @return
     */
    @GetMapping
    public PageResult<List<ApplicationResDTO>> findAll(@RequestParam(defaultValue = "1", value = "page_index") Integer pageIndex,
                                                       @RequestParam(defaultValue = "10", value = "page_size") Integer pageSize,
                                                       @RequestParam(defaultValue = "updated_at", value = "order_filed") String orderFiled,
                                                       @RequestParam(defaultValue = "desc", value = "order_rule") String orderRule,
                                                       @RequestParam Map<String, Object> params) {
        return applicationService.getAppOrderList(params, pageIndex, pageSize, orderFiled, orderRule);

    }

    /**
     * 获取应用权限列表
     *
     * @param appId
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @param params
     * @return
     */
    @GetMapping("/{appId}/privileges")
    public PageResult<List<PrivilegeResDTO>> getPrivilegeByAppId(@PathVariable Integer appId,
                                                                 @RequestParam(defaultValue = "0", value = "role_id") Integer roleId,
                                                                 @RequestParam(value = "reversal", defaultValue = "false", required = false) boolean reversal,
                                                                 @RequestParam(defaultValue = "1", value = "page_index") Integer pageIndex,
                                                                 @RequestParam(defaultValue = "10", value = "page_size") Integer pageSize,
                                                                 @RequestParam(defaultValue = "privilege_id", value = "order_filed") String orderFiled,
                                                                 @RequestParam(defaultValue = "desc", value = "order_rule") String orderRule,
                                                                 @RequestParam Map<String, Object> params) {
        checkSearch(params);

        PageParam pageParam = new PageParam(pageIndex, pageSize, orderFiled, orderRule);

        return applicationService.getPrivilegeOrderList(appId, roleId, reversal, params, pageParam);
    }

    /**
     * 获取角色拥有的权限列表
     * /v0.1/applications/{app_id}/roles/{role_id}/privileges
     *
     * @param appId
     * @param roleId
     * @return
     */
    @GetMapping("/{appId}/roles/{role_id}/privileges")
    public PageResult<List<PrivilegeResDTO>> getRolePrivileges(@PathVariable("appId") Integer appId,
                                                               @PathVariable("role_id") Integer roleId,
                                                               @RequestParam(value = "reversal", defaultValue = "false", required = false) boolean reversal,
                                                               @RequestParam(defaultValue = "1", value = "page_index") Integer pageIndex,
                                                               @RequestParam(defaultValue = "10", value = "page_size") Integer pageSize,
                                                               @RequestParam(defaultValue = "created_at", value = "order_filed") String orderFiled,
                                                               @RequestParam(defaultValue = "desc", value = "order_rule") String orderRule,
                                                               @RequestParam Map<String, Object> params) {

        checkSearch(params);

        PageParam pageParam = new PageParam(pageIndex, pageSize, orderFiled, orderRule);

        return applicationService.getPrivilegeOrderList(appId, roleId, reversal, params, pageParam);
    }

    private void checkSearch(Map<String, Object> params) {
        if (params.get("search") != null) {
            if ("'".equals(params.get("search").toString())) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }
            String search = params.get("search").toString().replace("%", "\\%");
            params.put("search", search);
        }
    }
}
