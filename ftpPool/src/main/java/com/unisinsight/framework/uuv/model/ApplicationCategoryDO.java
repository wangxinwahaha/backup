/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 应用类型实体类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:03
 * @since 1.0
 */
@Table(name = "uuv.application_category")
public class ApplicationCategoryDO {
    @Id
    private Integer id;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return category_code
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * @return category_name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ApplicationCategoryDO{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}