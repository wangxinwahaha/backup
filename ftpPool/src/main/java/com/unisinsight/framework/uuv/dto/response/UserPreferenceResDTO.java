/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:05
 * @since 1.0
 */
public class UserPreferenceResDTO implements Serializable {
    private String fieldKey;

    private String fieldValue;

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "UserPreferenceResDTO{" +
                "fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}
