/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 用户岗位关系实体类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:16
 * @since 1.0
 */
@Table(name = "uuv.user_position_mapping")
public class UserPositionMappingDO {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "position_id")
    private Integer positionId;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return position_id
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * @param positionId
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "UserPositionMappingDO{" +
                "userId=" + userId +
                ", positionId=" + positionId +
                '}';
    }
}