/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.PrivilegeDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限manager层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 9:48
 * @since 1.0
 */
@Component
public class PrivilegeManager extends AbstractMapperManager<PrivilegeDO> {

    public PrivilegeDO getPrivilegeBy(Integer appId, String privilegeCode) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        criteria.andEqualTo("privilegeCode", privilegeCode);
        return selectOne(criteria);
    }

    public boolean checkAllExist(Integer appId, Set<Integer> ids) {
        Set<Integer> idSet = new HashSet<>(ids.size());
        idSet.addAll(ids);
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        criteria.andIn("privilegeId", idSet);
        if (idSet.size() != count(criteria)) {
            return false;
        }

        return true;
    }

    public PrivilegeDO getPrivilegeBy(Integer appId, Integer privilegeId) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        criteria.andEqualTo("privilegeId", privilegeId);
        return selectOne(criteria);
    }

    public boolean checkAllExist(Integer appId, Integer priId) {
        Set<Integer> ids = new HashSet<>(1);
        return checkAllExist(appId, ids);
    }

    public Integer deletePriBy(Integer appId) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("appId", appId);
        deleteByCriteria(criteria);
        return 0;
    }
}
