/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.UserHandler;
import com.unisinsight.framework.uuv.dto.request.PrivilegeReqDTO;
import com.unisinsight.framework.uuv.dto.response.PrivilegeResDTO;
import com.unisinsight.framework.uuv.manager.ApplicationManager;
import com.unisinsight.framework.uuv.manager.PrivilegeManager;
import com.unisinsight.framework.uuv.model.PrivilegeDO;
import com.unisinsight.framework.uuv.service.PrivilegeService;

import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * description 权限实现类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:19
 * @since 1.0
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Resource
    private ApplicationManager applicationManager;

    @Resource
    private PrivilegeManager privilegeManager;

    /**
     * 新增权限
     *
     * @param privilegeReq
     * @param appId
     * @return
     */
    @Override
    public PrivilegeResDTO save(PrivilegeReqDTO privilegeReq, Integer appId) {

        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        if (null == privilegeReq.getPrivilegeCode() || "".equals(privilegeReq.getPrivilegeCode())) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "权限code为空");
        }

        if (null == privilegeReq.getPrivilegeName() || "".equals(privilegeReq.getPrivilegeName())) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "权限Name为空");
        }

        if (null == privilegeReq.getDataType()) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "数据类型为空");
        }

        if (privilegeManager.getPrivilegeBy(appId, privilegeReq.getPrivilegeCode()) !=null){
            throw FrameworkException.of(ErrorCode.PRIVILEGE_CODE_EXIST);
        }

        PrivilegeDO privilege = BeanConvert.convert(privilegeReq, PrivilegeDO.class);
        privilege.setCreatedAt(new Date());
        privilege.setCreatedBy(UserHandler.userCode());
        privilege.setAppId(appId);
        privilege.setStatus(0);

        privilegeManager.insert(privilege);

        return BeanConvert.convert(privilege, PrivilegeResDTO.class);
    }

    /**
     * 删除权限（支持批量刪除）
     *
     * @param appId,
     * @param privilegeIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer appId, List<Integer> privilegeIds) {

        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        //验证
        Set<Integer> idSet = new HashSet<>(privilegeIds);
        if (!privilegeManager.checkAllExist(appId, idSet)) {
            throw FrameworkException.of(ErrorCode.PRIVILEGE_NOT_EXIST);
        }

        privilegeManager.delete(idSet);

    }

    /**
     * 修改权限
     *
     * @param updateDTO
     * @param appId
     * @param privilegeId
     * @return
     */
    @Override
    public PrivilegeResDTO update(PrivilegeReqDTO updateDTO, Integer appId, Integer privilegeId) {

        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        //判断权限是否存在
        PrivilegeDO privilege = privilegeManager.getPrivilegeBy(appId, privilegeId);
        if (privilege == null) {
            throw FrameworkException.of(ErrorCode.PRIVILEGE_NOT_EXIST);
        }

        //是否修改了权限code
        if (updateDTO.getPrivilegeCode() != null && !updateDTO.getPrivilegeCode().equals(privilege.getPrivilegeCode())) {
            throw new RuntimeException("you can not change privilegeCode");
        }

        privilege.setPrivilegeName(updateDTO.getPrivilegeName());
        privilege.setDataType(updateDTO.getDataType());
        privilege.setDescription(updateDTO.getDescription());
        privilege.setUpdatedBy(UserHandler.userCode());
        privilege.setStatus(updateDTO.getStatus());
        privilege.setUpdatedAt(new Date());

        privilegeManager.update(privilege);

        return BeanConvert.convert(privilege, PrivilegeResDTO.class);

    }
}
