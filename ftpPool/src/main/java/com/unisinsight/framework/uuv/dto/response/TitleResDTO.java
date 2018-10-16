/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * description 数据响应对象
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:05
 * @since 1.0
 */
public class TitleResDTO implements Serializable {
    @Id
    @Column(name = "title_id")
    private Integer titleId;

    @Column(name = "title_code")
    private String titleCode;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
     * @return title_code
     */
    public String getTitleCode() {
        return titleCode;
    }

    /**
     * @param titleCode
     */
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    /**
     * @return title_name
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * @param titleName
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    @Override
    public String toString() {
        return "TitleResDTO{" +
                "titleId=" + titleId +
                ", titleCode='" + titleCode + '\'' +
                ", titleName='" + titleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
