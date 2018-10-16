/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import com.unisinsight.framework.uuv.model.UserPreferenceDO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * description 数据请求对象
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:59
 * @since 1.0
 */
public class UserInfoReqDTO extends BaseReqDTO {
    private Float displayOrder;

    private Integer orgId;

    private Integer[] positionIds;

    private Integer titleId;

    @Length(max = 16, message = "用户名长度超过16")
    @NotBlank(message = "用户名为空")
    private String userName;

    @Length(max = 32, message = "密码长度超过32")
    private String password;

    @Length(max = 32, message = "邮箱名称长度超过32")
    @NotBlank(message = "邮箱为空")
    private String email;

    @NotNull(message = "性别为空")
    private Integer gender;

    private Date birthday;

    @Length(max = 128, message = "地址长度超过128")
    private String address;

    @Length(max = 11, message = "工作号码长度超过11")
    private String workPhone;

    @Length(max = 11, message = "私人号码长度超过11")
    private String cellPhone;

    private List<UserPreferenceDO> preferences;

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }


    public Integer[] getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(Integer[] positionIds) {
        this.positionIds = positionIds;
    }

    public List<UserPreferenceDO> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<UserPreferenceDO> preferences) {
        this.preferences = preferences;
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

    @Override
    public String toString() {
        return "UserInfoReqDTO{" +
                "displayOrder=" + displayOrder +
                ", orgId=" + orgId +
                ", positionIds=" + Arrays.toString(positionIds) +
                ", titleId=" + titleId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", preferences=" + preferences +
                '}';
    }
}
