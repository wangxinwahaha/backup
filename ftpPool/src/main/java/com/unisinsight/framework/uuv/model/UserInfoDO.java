/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import java.util.Date;
import javax.persistence.*;

/**
 * 用户信息实体类
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:15
 * @since 1.0
 */
@Table(name = "uuv.user_info")
public class UserInfoDO {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "title_id")
    private Integer titleId;

    @Column(name = "user_code", insertable = false)
    private String userCode;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String email;

    private Integer gender;

    private Date birthday;

    private String address;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    private Integer status;

    private Integer deleted;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return created_at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    /**
     * @return updated_at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    /**
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "userId=" + userId +
                ", titleId=" + titleId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", deleted=" + deleted +
                ", reserved1='" + reserved1 + '\'' +
                ", reserved2='" + reserved2 + '\'' +
                ", reserved3='" + reserved3 + '\'' +
                '}';
    }
}