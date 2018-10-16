/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.manager;

import com.unisinsight.framework.uuv.model.UserPreferenceDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户扩展字段manager层
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:51
 * @since 1.0
 */
@Component
public class UserPreferenceManager extends AbstractMapperManager<UserPreferenceDO> {
    public void deleteByUserId(Integer userId) {
        UserPreferenceDO preference = new UserPreferenceDO();
        preference.setUserId(userId);
        delete(preference);
    }
}