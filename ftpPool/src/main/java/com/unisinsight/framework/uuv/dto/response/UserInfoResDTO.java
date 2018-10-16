/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;
import java.util.Date;

/**
 * description 数据响应对象
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:05
 * @since 1.0
 */
public class UserInfoResDTO implements Serializable {
    private Integer userId;

    private Integer titleId;

    private String userCode;

    private String userName;

    private String email;

    private Integer gender;

    private Date birthday;

    private String address;

    private String workPhone;

    private String cellPhone;

    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return title_id
     */
    public Integer getTitleId() {
        return titleId;
    }

    /**
     * @param titleId
     */
    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    /**
     * @return user_code
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
     * @return work_phone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * @param workPhone
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * @return cell_phone
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * @param cellPhone
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }


    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserInfoResDTO{" +
                "titleId=" + titleId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", status=" + status +
                '}';
    }
}
