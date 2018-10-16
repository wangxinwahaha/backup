/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 组织扩展字段实体类
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:07
 * @since 1.0
 */
@Table(name = "uuv.organization_preference")
public class OrganizationPreferenceDO {
    @Id
    @Column(name = "organization_preference_id")
    private Integer organizationPreferenceId;

    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "field_key")
    private String fieldKey;

    @Column(name = "field_value")
    private String fieldValue;


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
        return "OrganizationPreferenceDO{" +
                "organizationPreferenceId=" + organizationPreferenceId +
                ", orgId=" + orgId +
                ", fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}