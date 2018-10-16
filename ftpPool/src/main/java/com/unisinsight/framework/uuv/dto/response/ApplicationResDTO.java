/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description 数据响应对象
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:00
 * @since 1.0
 */
public class ApplicationResDTO implements Serializable {

    private Integer appId;

    private String appCode;

    private String appName;

    private String defaultUrl;

    private String description;

    private Integer categoryId;


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

    @Override
    public String toString() {
        return "ApplicationResDTO{" +
                "appId=" + appId +
                ", appCode='" + appCode + '\'' +
                ", appName='" + appName + '\'' +
                ", defaultUrl='" + defaultUrl + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
