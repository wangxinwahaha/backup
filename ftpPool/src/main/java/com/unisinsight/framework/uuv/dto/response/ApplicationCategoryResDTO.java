/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * description 数据响应对象
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 9:59
 * @since 1.0
 */
public class ApplicationCategoryResDTO implements Serializable {
    @Id
    private Integer id;

    private String categoryCode;

    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ApplicationCategoryResDTO{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
