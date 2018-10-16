/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.utils;

import com.unisinsight.framework.uuv.common.Constants;
import com.unisinsight.framework.uuv.model.OrganizationExtraDO;

import java.util.HashMap;
import java.util.Map;

/**
 * 组织树
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:10
 * @since 1.0
 */
public class OrganizationTree {
    private Map<Integer, OrganizationExtraDO> orgIndex = new HashMap<>();

    private OrganizationExtraDO root;

    public void addChild(OrganizationExtraDO org){
        orgIndex.put(org.getOrgId(), org);
        if (org.getParentId().equals(Constants.EMPTY_UUID)){
            root = org;
        } else {
            OrganizationExtraDO parent = orgIndex.get(org.getParentId());
            if (parent == null) {
                root = org;
            } else {
                parent.addChild(org);
            }
        }
    }

    public OrganizationExtraDO getRoot() {
        return root;
    }
}
