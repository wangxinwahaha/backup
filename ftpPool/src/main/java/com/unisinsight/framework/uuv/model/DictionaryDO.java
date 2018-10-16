/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 字典实体类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:04
 * @since 1.0
 */
@Table(name = "uuv.dictionary")
public class DictionaryDO {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "item")
    private String item;

    @Column(name = "comments")
    private String comments;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "view_type")
    private String viewType;

    @Column(name = "display_order")
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
        return "DictionaryDO{" +
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