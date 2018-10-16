/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * description 数据请求对象
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 9:53
 * @since 1.0
 */
public class OrganizationPreferenceReqDTO implements Serializable {

    @NotEmpty
    private Integer orgId;

    @Length(max = 20)
    private String fieldKey;

    @Length(max = 20)
    private String fieldValue;

    /**
     * @return org_id
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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
        return "OrganizationPreferenceReqDTO{" +
                "orgId=" + orgId +
                ", fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}
