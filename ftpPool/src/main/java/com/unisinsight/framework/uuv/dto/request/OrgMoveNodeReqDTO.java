/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * description 数据请求对象
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/7 9:56
 * @since 1.0
 */
public class OrgMoveNodeReqDTO implements Serializable {
    private Integer targetId;
    private List<Integer> currentId;
    private Boolean moveOrg;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public List<Integer> getCurrentId() {
        return currentId;
    }

    public void setCurrentId(List<Integer> currentId) {
        this.currentId = currentId;
    }

    public boolean isMoveOrg() {
        return moveOrg;
    }

    public void setMoveOrg(boolean moveOrg) {
        this.moveOrg = moveOrg;
    }

    @Override
    public String toString() {
        return "OrgMoveNodeReqDTO{" +
                "targetId=" + targetId +
                ", currentId=" + currentId +
                ", moveOrg=" + moveOrg +
                '}';
    }
}
