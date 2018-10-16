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
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:58
 * @since 1.0
 */
public class UpdatePwdDTO implements Serializable {
    private Integer userId;

    @NotEmpty(message = "旧密码为空")
    @Length(max = 32)
    private String oldPassword;

    @NotEmpty(message = "新密码为空")
    @Length(max = 32)
    private String newPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePwdDTO{" +
                "userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
