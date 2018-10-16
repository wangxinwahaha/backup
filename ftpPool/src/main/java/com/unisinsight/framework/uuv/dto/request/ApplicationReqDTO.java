/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * description 应用DTO
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/09/06 17:12
 * @since 1.0
 */
public class ApplicationReqDTO extends BaseReqDTO {


    @Length(max = 16, message = "最大长度16")
    @NotBlank(message = "不能为空")
    private String appCode;

    @Length(max = 16, message = "最大长度16")
    @NotBlank(message = "不能为空")
    private String appName;

    @Length(max = 256, message = "最大长度256")
    @NotBlank(message = "不能为空")
    private String defaultUrl;

    private String description;

    private String createdBy;

    private String updatedBy;

    @NotNull(message = "categoryId 不能为空")
    private Integer categoryId;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "ApplicationReqDTO{" +
                "appCode='" + appCode + '\'' +
                ", appName='" + appName + '\'' +
                ", defaultUrl='" + defaultUrl + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
