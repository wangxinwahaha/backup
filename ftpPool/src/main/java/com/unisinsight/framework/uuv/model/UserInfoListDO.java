/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户列表实体类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:16
 * @since 1.0
 */
public class UserInfoListDO implements Serializable {

    private Integer userId;

    private String userCode;

    private String userName;

    private String email;

    private String gender;

    private Date birthday;

    private String address;

    private String workPhone;

    private String cellPhone;

    private Integer status;

    private String titleName;

    private String titleCode;

    private String orgCode;

    private String orgName;

    private String extendKey;

    private String extendValue;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getExtendKey() {
        return extendKey;
    }

    public void setExtendKey(String extendKey) {
        this.extendKey = extendKey;
    }

    public String getExtendValue() {
        return extendValue;
    }

    public void setExtendValue(String extendValue) {
        this.extendValue = extendValue;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
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
        return "UserInfoListDO{" +
                "userId=" + userId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", status=" + status +
                ", titleName='" + titleName + '\'' +
                ", titleCode='" + titleCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", extendKey='" + extendKey + '\'' +
                ", extendValue='" + extendValue + '\'' +
                '}';
    }
}
