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
public class OrganizationResDTO implements Serializable {

    private Integer orgId;

    private String orgCode;

    private String orgName;

    private String address;

    private String description;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * @return org_code
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return org_name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
        return "OrganizationResDTO{" +
                "orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
