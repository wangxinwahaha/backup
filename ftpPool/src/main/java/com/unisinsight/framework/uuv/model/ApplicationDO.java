/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import java.util.Date;
import javax.persistence.*;

/**
 * 应用实体类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:04
 * @since 1.0
 */
@Table(name = "uuv.application")
public class ApplicationDO {
    @Id
    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "app_code")
    private String appCode;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "default_url")
    private String defaultUrl;

    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    private Integer status;

    @Column(name = "deleted")
    private Integer deleted;

    private  Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return app_code
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * @param appCode
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * @return app_name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return default_url
     */
    public String getDefaultUrl() {
        return defaultUrl;
    }

    /**
     * @param defaultUrl
     */
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
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
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return create_at
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return updated_by
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return updated_at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        return "ApplicationDO{" +
                "appId=" + appId +
                ", appCode='" + appCode + '\'' +
                ", appName='" + appName + '\'' +
                ", defaultUrl='" + defaultUrl + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createAt=" + createAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", deleted=" + deleted +
                ", categoryId=" + categoryId +
                '}';
    }
}