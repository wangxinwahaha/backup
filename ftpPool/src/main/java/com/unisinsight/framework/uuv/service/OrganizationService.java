/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.unisinsight.framework.uuv.dto.request.OrganizationReqDTO;
import com.unisinsight.framework.uuv.dto.response.OrganizationResDTO;
import com.unisinsight.framework.uuv.model.OrganizationExtraDO;
import com.unisinsight.framework.uuv.model.UserInfoDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description 组织接口
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:12
 * @since 1.0
 */
public interface OrganizationService {

    /**
     * 新增
     *
     * @param reqDTO
     * @return
     */
    OrganizationResDTO save(OrganizationReqDTO reqDTO);


    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 更新
     *
     * @param updateDTO
     * @param orgId
     * @return
     */
    OrganizationResDTO update(OrganizationReqDTO updateDTO, Integer orgId);

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    OrganizationResDTO findById(Integer id);

    /**
     * 获取所有
     *
     * @return
     */
    List<OrganizationResDTO> findAll();

    /**
     * 获取子组织
     *
     * @param orgId
     * @return
     */
    List<OrganizationExtraDO> getChildOrg(Integer orgId);

    /**
     * 组织添加用户
     *
     * @param appId
     * @param users
     * @return
     */
    Integer addUsers(Integer appId, Set<Integer> users);

    /**
     * 组织删除用户
     *
     * @param orgId
     * @param userId
     */
    void deleteUser(Integer orgId, Integer userId);

    /**
     * 移动节点
     *
     * @param target
     * @param currentIds
     * @param isMoveOrg
     * @return
     */
    int moveNode(Integer target, List<Integer> currentIds, boolean isMoveOrg);

    /**
     * 通过id查找组织
     *
     * @param id
     * @return
     */
    Map<String, Object> findOrgById(Integer id);
}
