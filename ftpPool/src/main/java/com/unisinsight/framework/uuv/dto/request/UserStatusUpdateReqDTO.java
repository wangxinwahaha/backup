/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * description 数据请求对象
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:59
 * @since 1.0
 */
public class UserStatusUpdateReqDTO {

    @Range(min=0, max=1, message = "状态超过范围")
    @NotNull(message = "状态为空")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserStatusUpdateReqDTO{" +
                "status=" + status +
                '}';
    }
}
