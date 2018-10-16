/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description 数据响应对象
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:04
 * @since 1.0
 */
public class PrivilegeResDTO implements Serializable {

    private Integer privilegeId;

    private String privilegeCode;

    private String privilegeName;

    private Integer dataType;

    private String description;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    /**
     * @return data_type
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
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
        return "PrivilegeResDTO{" +
                "privilegeId=" + privilegeId +
                ", privilegeCode='" + privilegeCode + '\'' +
                ", privilegeName='" + privilegeName + '\'' +
                ", dataType=" + dataType +
                ", description='" + description + '\'' +
                '}';
    }
}
