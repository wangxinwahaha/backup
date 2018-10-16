/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 岗位实体类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:11
 * @since 1.0
 */
@Table(name = "uuv.position")
public class PositionDO {
    @Id
    @Column(name = "position_id")
    private Integer positionId;

    @Column(name = "position_code")
    private String positionCode;

    @Column(name = "position_name")
    private String positionName;

    private String description;

    private Float displayOrder;

    private Integer deleted;

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
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

    /**
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "PositionDO{" +
                "positionId=" + positionId +
                ", positionCode='" + positionCode + '\'' +
                ", positionName='" + positionName + '\'' +
                ", description='" + description + '\'' +
                ", displayOrder=" + displayOrder +
                ", deleted=" + deleted +
                '}';
    }
}