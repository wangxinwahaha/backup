/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.UserInfoDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户manager层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:51
 * @since 1.0
 */
@Component
public class UserInfoManager extends AbstractMapperManager<UserInfoDO> {
    public boolean checkAllExist(Set<Integer> ids){
        return checkAllExist(new ArrayList<>(ids));
    }

    public boolean checkAllExist(List<Integer> ids) {
        Set<Integer> idSet = new HashSet<>(ids.size());
        idSet.addAll(ids);
        Example.Criteria criteria = buildCriteria();
        criteria.andIn("userId", idSet);
        if (idSet.size() != count(criteria)) {
            return false;
        }
        return true;
    }

    public String selectPasswordBy(Integer userId) {
        Example.Criteria criteria = buildCriteria();
        criteria.andEqualTo("userId", userId);
        return selectOne(criteria, "password").getPassword();
    }
}
