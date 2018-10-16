/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description 分页计算
 *
 * @author tangang [KF.tangang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 每页条数
     */
    private int pageSize;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        this.pageNum = Integer.parseInt(params.get("pageNum").toString());
        this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        this.put("offset", (pageNum - 1) * pageSize);
        this.put("pageNum", pageNum);
        this.put("pageSize", pageSize);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
