/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/09/11 16:11
 * @since 1.0
 */
public class LoginReqDTO implements Serializable {
    @Length(max = 16, message = "用户名长度超过16")
    @NotBlank(message = "用户名为空")
    String userCode;

    @Length(max = 32, message = "密码长度超过32")
    @NotBlank(message = "密码为空")
    String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
