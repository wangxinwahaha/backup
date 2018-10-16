/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import com.unisinsight.framework.uuv.dto.response.RolePrivilegeResDTO;
import com.unisinsight.framework.uuv.manager.ApplicationManager;
import com.unisinsight.framework.uuv.manager.PrivilegeManager;
import com.unisinsight.framework.uuv.manager.RoleManager;
import com.unisinsight.framework.uuv.manager.RolePriMappingManager;
import com.unisinsight.framework.uuv.mapper.RolePrivilegeMappingMapper;
import com.unisinsight.framework.uuv.model.PrivilegeDO;
import com.unisinsight.framework.uuv.model.RolePrivilegeMappingDO;
import com.unisinsight.framework.uuv.service.RolePrivilegeMappingService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * description 角色权限实现类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:19
 * @since 1.0
 */
@Service
public class RolePrivilegeMappingServiceImpl implements RolePrivilegeMappingService {
    @Resource
    private RolePrivilegeMappingMapper rolePriMappingDOMapper;

    @Resource
    private ApplicationManager appManager;

    @Resource
    private RoleManager roleManager;

    @Resource
    private PrivilegeManager priManager;

    @Resource
    private RolePriMappingManager rolePriMappingManager;

    /**
     * 角色删除权限
     *
     * @param appId
     * @param roleId
     * @param privilegeList
     * @return
     */
    @Override
    public void deleteRolePrivilege(Integer appId, Integer roleId, List<Integer> privilegeList) {
        //判断应用是否存在
        if (appManager.get(appId) == null) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }
        //判断应用下角色是否存在
        if (roleManager.getRoleBy(appId, roleId) == null) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }
        //参数校验
        RolePrivilegeMappingDO rolePrivilegeDO = new RolePrivilegeMappingDO();
        for (Integer priId : privilegeList) {
            PrivilegeDO pri = priManager.get(priId);
            //判断权限是否存在
            if (pri == null) {
                throw FrameworkException.of(ErrorCode.PRIVILEGE_NOT_EXIST);
            }
            //判断应用是否拥有该权限
            if (!pri.getAppId().equals(appId)) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR,
                        "应用" + appId + "没有权限" + priId);
            }

            //判断角色是否拥有该权限
            rolePrivilegeDO.setPrivilegeId(priId);
            rolePrivilegeDO.setRoleId(roleId);
            if (rolePriMappingManager.selectAll().size() == 0) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR,
                        "角色" + roleId + "不拥有权限" + priId);
            }
        }
        //批量删除
        Condition condition = new Condition(RolePrivilegeMappingDO.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        criteria.andIn("privilegeId", privilegeList);
        condition.and(criteria);
        rolePriMappingDOMapper.deleteByCondition(condition);

    }

    /**
     * 角色添加权限（批量添加）
     *
     * @param appId
     * @param roleId
     * @param privilegeList 权限ID列表
     * @return
     */
    @Override
    public List<RolePrivilegeResDTO> addPrivileges(Integer appId, Integer roleId, Set<Integer> privilegeList) {
        //应用是否存在
        if (appManager.get(appId) == null) {
            throw FrameworkException.of(ErrorCode.APP_NOT_EXIST);
        }
        //判断应用下角色是否存在
        if (roleManager.getRoleBy(appId, roleId) == null) {
            throw FrameworkException.of(ErrorCode.ROLE_NOT_EXIST);
        }

        List<RolePrivilegeMappingDO> saveList = new ArrayList<>();
        for (Integer priId : privilegeList) {
            //判断权限是否存在
            PrivilegeDO pri = priManager.get(priId);
            if (pri == null) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR,
                        "权限" + priId + "不存在");
            }
            //判断应用是都拥有该权限
            if (!pri.getAppId().equals(appId)) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR,
                        "应用" + appId + "没有权限" + priId);
            }
            //判断角色是否拥有该权限
            if (rolePriMappingManager.getRolePri(roleId, priId).size() != 0) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR,
                        "角色已拥有权限" + priId);
            }

            RolePrivilegeMappingDO save = new RolePrivilegeMappingDO();
            save.setRoleId(roleId);
            save.setPrivilegeId(priId);
            saveList.add(save);
        }
        //批量添加
        rolePriMappingManager.insert(saveList);

        return BeanConvert.convertList(saveList, RolePrivilegeResDTO.class);

    }

}
