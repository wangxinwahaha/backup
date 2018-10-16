/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 用户扩展字段实体类
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:17
 * @since 1.0
 */
@Table(name = "uuv.user_preference")
public class UserPreferenceDO {
    @Id
    @Column(name = "user_preference_id")
    private Integer userPreferenceId;

    @Column(name = "user_id")
    private Integer userId;

    @NotBlank(message = "key is null")
    private String fieldKey;

    @Column(name = "field_value")
    private String fieldValue;

    /**
     * @return user_preference_id
     */
    public Integer getUserPreferenceId() {
        return userPreferenceId;
    }

    /**
     * @param userPreferenceId
     */
    public void setUserPreferenceId(Integer userPreferenceId) {
        this.userPreferenceId = userPreferenceId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return field_key
     */
    public String getFieldKey() {
        return fieldKey;
    }

    /**
     * @param fieldKey
     */
    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    /**
     * @return field_value
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValue
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "UserPreferenceDO{" +
                "userPreferenceId=" + userPreferenceId +
                ", userId=" + userId +
                ", fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}