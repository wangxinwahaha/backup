/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description 数据响应对象
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:03
 * @since 1.0
 */
public class PositionResDTO implements Serializable {
    private Integer positionId;

    private String positionCode;

    private String positionName;

    private String description;

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

    /**
     * @return position_code
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * @param positionCode
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    /**
     * @return position_name
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * @param positionName
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PositionResDTO{" +
                "positionId=" + positionId +
                ", positionCode='" + positionCode + '\'' +
                ", positionName='" + positionName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
