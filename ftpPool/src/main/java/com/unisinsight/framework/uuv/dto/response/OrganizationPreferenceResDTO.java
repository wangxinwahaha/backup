/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:02
 * @since 1.0
 */
public class OrganizationPreferenceResDTO implements Serializable {

    private Integer organizationPreferenceId;

    private Integer orgId;

    private String fieldKey;

    private String fieldKeyValue;

    /**
     * @return organization_preference_id
     */
    public Integer getOrganizationPreferenceId() {
        return organizationPreferenceId;
    }

    /**
     * @param organizationPreferenceId
     */
    public void setOrganizationPreferenceId(Integer organizationPreferenceId) {
        this.organizationPreferenceId = organizationPreferenceId;
    }

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
    public String getFieldKeyValue() {
        return fieldKeyValue;
    }

    /**
     * @param fieldKeyValue
     */
    public void setFieldKeyValue(String fieldKeyValue) {
        this.fieldKeyValue = fieldKeyValue;
    }

    @Override
    public String toString() {
        return "OrganizationPreferenceResDTO{" +
                "organizationPreferenceId=" + organizationPreferenceId +
                ", orgId=" + orgId +
                ", fieldKey='" + fieldKey + '\'' +
                ", fieldKeyValue='" + fieldKeyValue + '\'' +
                '}';
    }
}
