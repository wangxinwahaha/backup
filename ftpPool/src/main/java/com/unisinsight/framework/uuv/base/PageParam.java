/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.base;

/**
 * 分页相关
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class PageParam {

    /**
     * 总数量
     */
    private int total;

    /**
     * 页码，从1开始
     */
    private int pageIndex;

    /**
     * 页面大小
     */
    private int pageSize;

    private String orderFiled;

    private String orderRule;

    public PageParam() {}

    public PageParam(int pageIndex, int pageSize, String orderFiled, String orderRule) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.orderFiled = orderFiled;
        this.orderRule = orderRule;
    }

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
