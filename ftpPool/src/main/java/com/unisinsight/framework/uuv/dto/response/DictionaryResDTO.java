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
public class DictionaryResDTO implements Serializable {

    private Integer id;

    private String key;

    private String value;

    private String item;

    private String comments;

    private Integer appId;

    private String dataType;

    private String viewType;

    private Float displayOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "DictionaryResDTO{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", item='" + item + '\'' +
                ", comments='" + comments + '\'' +
                ", appId=" + appId +
                ", dataType='" + dataType + '\'' +
                ", viewType='" + viewType + '\'' +
                ", displayOrder=" + displayOrder +
                '}';
    }
}