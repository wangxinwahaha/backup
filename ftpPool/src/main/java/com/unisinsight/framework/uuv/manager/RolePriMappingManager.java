/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.RolePrivilegeMappingDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 角色权限manager层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 9:49
 * @since 1.0
 */
@Component
public class RolePriMappingManager extends AbstractMapperManager<RolePrivilegeMappingDO> {
    /**
     * 查询制定角色和权限
     *
     * @param roleId
     * @param privilegeId
     * @return
     */
    public List<RolePrivilegeMappingDO> getRolePri(Integer roleId, Integer privilegeId) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("roleId", roleId);
        criteria.andEqualTo("privilegeId", privilegeId);
        return selectAll(criteria);
    }
}
