/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * description 字典请求DTO
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 9:52
 * @since 1.0
 */
public class DictionaryReqDTO implements Serializable {


    @Length(max = 16, message = "最大长度16")
    @NotBlank(message = "key 不能为空")
    private String key;

    @NotBlank(message = "value 不能为空")
    @Length(max = 256, message = "最大长度256")
    private String value;

    @NotBlank(message = "item 不能为空")
    @Length(max = 32, message = "最大长度32")
    private String item;

    @NotBlank(message = "comments 不能为空")
    @Length(max = 256, message = "最大长度256")
    private String comments;

    private Integer appId;

    @NotBlank(message = "dataType 不能为空")
    @Length(max = 10, message = "最大长度10")
    private String dataType;

    @NotBlank(message = "viewType 不能为空")
    @Length(max = 10, message = "最大长度10")
    private String viewType;

    private Float displayOrder;

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
        return "DictionaryReqDTO{" +
                "key='" + key + '\'' +
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