/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.UserHandler;
import com.unisinsight.framework.uuv.dto.request.RoleReqDTO;
import com.unisinsight.framework.uuv.dto.response.RoleResDTO;
import com.unisinsight.framework.uuv.dto.response.UserRoleMappingResDTO;
import com.unisinsight.framework.uuv.manager.ApplicationManager;
import com.unisinsight.framework.uuv.manager.RoleManager;
import com.unisinsight.framework.uuv.manager.UserInfoManager;
import com.unisinsight.framework.uuv.mapper.UserRoleMappingMapper;
import com.unisinsight.framework.uuv.model.*;
import com.unisinsight.framework.uuv.service.RoleService;

import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

import static com.unisinsight.framework.uuv.common.exception.ErrorCode.USER_EXIST;

/**
 * description 角色实现类
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:18
 * @since 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private UserRoleMappingMapper userRoleMappingMapper;

    @Resource
    private RoleManager roleManager;

    @Resource
    private ApplicationManager applicationManager;

    @Resource
    private UserInfoManager userInfoManager;

    /**
     * 新增角色
     *
     * @param roleReq
     * @param appId
     * @return
     */
    @Override
    public RoleResDTO save(RoleReqDTO roleReq, Integer appId) {
        //判断角色code是否已存在
        RoleDO role = roleManager.getRoleBy(appId, roleReq.getRoleCode());
        if (role != null) {
            throw FrameworkException.of(ErrorCode.ROLE_EXIST);
        }
        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        RoleDO roleSave = BeanConvert.convert(roleReq, RoleDO.class);
        roleSave.setAppId(appId);
        roleSave.setCreatedAt(new Date());
        roleSave.setCreatedBy(UserHandler.userCode());
        roleSave.setStatus(0);
        //添加新角色
        roleManager.insert(roleSave);
        return BeanConvert.convert(roleSave, RoleResDTO.class);
    }

    /**
     * 删除角色
     *
     * @param appId
     * @param roleIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer appId, List<Integer> roleIds) {
        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        //判断角色是否存在
        Set<Integer> idSet = new HashSet<>(roleIds);
        if (!roleManager.checkAllExist(appId, idSet)) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }

        for (Integer id : roleIds) {
            RoleDO roleDO = new RoleDO();
            roleDO.setUpdatedAt(new Date());
            roleDO.setUpdatedBy(UserHandler.userCode());
            roleDO.setRoleId(id);
            roleManager.updateSelective(roleDO);
        }

        roleManager.delete(idSet);
    }

    /**
     * 修改角色
     *
     * @param role
     * @param appId
     * @param roleId
     * @return
     */
    @Override
    public RoleResDTO update(RoleReqDTO role, Integer appId, Integer roleId) {
        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }
        //判断角色是否存在
        RoleDO oldRole = roleManager.getRoleBy(appId, roleId);
        if (oldRole == null) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }
        //若修改了code，则抛异常
        if (role.getRoleCode() != null && !oldRole.getRoleCode().equals(role.getRoleCode())) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "您无权修改应用code！");
        }

        oldRole.setRoleName(role.getRoleName());
        oldRole.setUpdatedAt(new Date());
        oldRole.setUpdatedBy(UserHandler.userCode());
        oldRole.setDescription(role.getDescription());

        roleManager.update(oldRole);
        return BeanConvert.convert(oldRole, RoleResDTO.class);
    }

    /**
     * 获取角色列表
     *
     * @param appId
     * @return List<RoleDO>
     */
    @Override
    public List<RoleResDTO> findAll(Integer appId) {
        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        Example.Criteria criteria = roleManager.buildCriteria();
        criteria.andEqualTo("appId", appId);
        return BeanConvert.convertList(roleManager.selectAll(criteria), RoleResDTO.class);
    }

    /**
     * 角色新增用户（支持批量新增）
     *
     * @param userIds
     * @param appId
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserRoleMappingResDTO> saveNewUserToRole(List<Integer> userIds, Integer appId, Integer roleId) {

        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        //判断角色是否存在
        RoleDO role = roleManager.getRoleBy(appId, roleId);
        if (role == null) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }

        if (!userInfoManager.checkAllExist(userIds)) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
        }

        List<UserRoleMappingDO> result = new ArrayList<>();

        userIds.forEach(userId -> {
            //TODO 代码优化
            UserRoleMappingDO userRoleMappingDO = new UserRoleMappingDO();
            userRoleMappingDO.setRoleId(roleId);
            userRoleMappingDO.setUserId(userId);
            if (userRoleMappingMapper.selectOne(userRoleMappingDO) != null) {
                throw FrameworkException.of(USER_EXIST);
            }

            userRoleMappingMapper.insert(userRoleMappingDO);

            result.add(userRoleMappingDO);
        });

        return BeanConvert.convertList(result, UserRoleMappingResDTO.class);
    }


    /**
     * 角色删除用户（支持批量删除）
     *
     * @param ids
     * @param appId
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserFromRole(Set<Integer> ids, Integer appId, Integer roleId) {

        //判断应用是否存在
        if (!applicationManager.isExist(appId)) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }

        //判断角色是否存在
        RoleDO role = roleManager.getRoleBy(appId, roleId);
        if (role == null) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }

        userInfoManager.checkAllExist(ids);

        for (Integer userId : ids) {
            //TODO 代码优化
            UserRoleMappingDO userRoleMappingDO = new UserRoleMappingDO();
            userRoleMappingDO.setRoleId(roleId);
            userRoleMappingDO.setUserId(userId);
            if (userRoleMappingMapper.selectOne(userRoleMappingDO) == null) {
                throw FrameworkException.of(ErrorCode.USER_NOT_EXIST);
            }

            userRoleMappingMapper.delete(userRoleMappingDO);
        }
    }

}
