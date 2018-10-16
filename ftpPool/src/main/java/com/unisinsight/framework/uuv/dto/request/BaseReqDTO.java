/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * description 基础请求DTO
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/7 9:50
 * @since 1.0
 */
public class BaseReqDTO implements Serializable {
    @Length(max = 200)
    private String description;

    @Length(max = 16)
    private String createdBy;

    @Length(max = 16)
    private String updatedBy;

//    private Integer status = 0;

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

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * @return updated_by
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "BaseReqDTO{" +
                "description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
