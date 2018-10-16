/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.RoleDO;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import com.unisinsight.framework.uuv.model.UserRoleMappingDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色mapper层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:02
 * @since 1.0
 */
public interface UserRoleMappingMapper extends Mapper<UserRoleMappingDO> {
}