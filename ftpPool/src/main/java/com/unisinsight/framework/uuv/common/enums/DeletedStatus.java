/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.enums;

/**
 * description 伪删常量
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public enum DeletedStatus {
    /**
     * 已删除为DELETED，存在为EXIST
     */
    DELETED(-1),EXIST(0);

    private Integer status;
    DeletedStatus(Integer status){
        this.status = status;
    }

    public Integer value(){
        return status;
    }
}
