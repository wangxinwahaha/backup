/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * description 数据请求对象
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 9:55
 * @since 1.0
 */
public class OrganizationReqDTO extends BaseReqDTO {

    private Float displayOrder;

    private Integer parent = 0;

    @NotEmpty
    @Length(max = 32)
    private String orgName;

    @Length(max = 128)
    private String address;

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    private List<OrganizationPreferenceReqDTO> preference;

    public List<OrganizationPreferenceReqDTO> getPreference() {
        return preference;
    }

    public void setPreference(List<OrganizationPreferenceReqDTO> preference) {
        this.preference = preference;
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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "OrganizationReqDTO{" +
                "displayOrder=" + displayOrder +
                ", parent=" + parent +
                ", orgName='" + orgName + '\'' +
                ", address='" + address + '\'' +
                ", preference=" + preference +
                '}';
    }
}
