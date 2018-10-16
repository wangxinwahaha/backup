/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 职级实体类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:14
 * @since 1.0
 */
@Table(name = "uuv.title")
public class TitleDO {
    @Id
    @Column(name = "title_id")
    private Integer titleId;

    @Column(name = "title_code")
    private String titleCode;

    @Column(name = "title_name")
    private String titleName;

    private String description;

    private Float displayOrder;

    private Integer deleted;

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return title_id
     */
    public Integer getTitleId() {
        return titleId;
    }

    /**
     * @param titleId
     */
    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    /**
     * @return title_code
     */
    public String getTitleCode() {
        return titleCode;
    }

    /**
     * @param titleCode
     */
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    /**
     * @return title_name
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * @param titleName
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
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
        return "TitleDO{" +
                "titleId=" + titleId +
                ", titleCode='" + titleCode + '\'' +
                ", titleName='" + titleName + '\'' +
                ", description='" + description + '\'' +
                ", displayOrder=" + displayOrder +
                ", deleted=" + deleted +
                '}';
    }
}