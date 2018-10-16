/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * description 数据请求对象
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 9:57
 * @since 1.0
 */
public class PrivilegeReqDTO extends BaseReqDTO {


    @Length(max = 16, message = "权限code长度超过16")
    private String privilegeCode;

    @Length(max = 32, message = "权限名称长度超过16")
    @NotBlank(message = "权限名称为空")
    private String privilegeName;

    @Range(min = 0, max = 1, message = "权限状态超出范围")
    @NotNull(message = "权限状态为空")
    private Integer dataType;

    @Length(max = 200, message = "描述信息长度超过200")
    private String description;

    @Length(max = 32, message = "创建人长度超过32")
    private String createdBy;

    @Length(max = 32, message = "修改人长度超过32")
    private String updatedBy;

    private String appId;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    @Override
    public String toString() {
        return "PrivilegeReqDTO{" +
                "privilegeCode='" + privilegeCode + '\'' +
                ", privilegeName='" + privilegeName + '\'' +
                ", dataType=" + dataType +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", appId='" + appId + '\'' +
                ", status=" + status +
                '}';
    }
}
