/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.util.List;

/**
 * description
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/09/13 08:48
 * @since 1.0
 */
public class UserAppResDTO {
    private String defaultUrl;

    private Integer appId;

    private String appCode;

    private String appName;

    private List<PrivilegeResDTO> menus;

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<PrivilegeResDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<PrivilegeResDTO> menus) {
        this.menus = menus;
    }
}
