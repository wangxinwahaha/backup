/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.common.utils.UserHandler;
import com.unisinsight.framework.uuv.dto.request.OrgMoveNodeReqDTO;
import com.unisinsight.framework.uuv.dto.request.OrganizationReqDTO;
import com.unisinsight.framework.uuv.dto.response.OrganizationResDTO;
import com.unisinsight.framework.uuv.model.OrganizationExtraDO;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import com.unisinsight.framework.uuv.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 组织Controller层
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/organizations")
public class OrganizationController {

    private final static String USER_ID = "user_id";

    @Resource
    private OrganizationService organizationService;

    @PostMapping
    public OrganizationResDTO add(@RequestBody @Valid OrganizationReqDTO organizationReqDTO) {
        return organizationService.save(organizationReqDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        organizationService.deleteById(id);
    }

    @PutMapping("/{id}")
    public OrganizationResDTO update(@PathVariable(value = "id") Integer id,
                                     @RequestBody @Valid OrganizationReqDTO organizationReqDTO) {
        return organizationService.update(organizationReqDTO, id);
    }

    /**
     * 获取组织架构接口
     * @param orgId，若传入0，则获取整个组织结构树
     * @return 返回树形结构
     */
    @GetMapping("/{orgId}/childs")
    public List<OrganizationExtraDO> getChildOrg(@PathVariable("orgId") Integer orgId){
        return organizationService.getChildOrg(orgId);
    }

    @PostMapping(value = "/{orgId}/users")
    public Integer addUser(@PathVariable Integer orgId, @RequestBody Map<String, Object> users){
        if (users.get(USER_ID) != null && users.get(USER_ID) instanceof List){
            List<Integer> userId = (List<Integer>) users.get("user_id");
            return organizationService.addUsers(orgId, new HashSet<>(userId));
        }
        return 0;
    }

    @PutMapping(value = "/move-nodes")
    public Integer moveNodes(@RequestBody OrgMoveNodeReqDTO moveNode){
        organizationService.moveNode(moveNode.getTargetId(), moveNode.getCurrentId(), moveNode.isMoveOrg());
        return 0;
    }

    @DeleteMapping(value = "/{orgId}/users/{userId}")
    public void deleteUser(@PathVariable Integer orgId, @PathVariable Integer userId){
        organizationService.deleteUser(orgId, userId);
    }

    @GetMapping("/{orgId}")
    public Map<String, Object> detail(@PathVariable Integer orgId) {
        return organizationService.findOrgById(orgId);

    }

}
