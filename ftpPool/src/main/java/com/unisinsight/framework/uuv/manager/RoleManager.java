/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.RoleDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色manager层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:49
 * @since 1.0
 */
@Component
public class RoleManager extends AbstractMapperManager<RoleDO> {

    public RoleDO getRoleBy(Integer appId, String roleCode) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        criteria.andEqualTo("roleCode", roleCode);
        return selectOne(criteria);
    }

    public RoleDO getRoleBy(Integer appId, Integer roleId) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        criteria.andEqualTo("roleId", roleId);
        return selectOne(criteria);
    }

    public boolean checkAllExist(Integer appId, Set<Integer> ids) {
        Set<Integer> idSet = new HashSet<>(ids.size());
        idSet.addAll(ids);
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        criteria.andIn("roleId", idSet);
        if (idSet.size() != count(criteria)) {
            return false;
        }
        return true;
    }

    public void deleteRoleBy(Integer appId) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        deleteByCriteria(criteria);
    }
}
