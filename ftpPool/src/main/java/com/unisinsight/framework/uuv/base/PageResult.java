/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.base;

/**
 * 分页返回结果
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class PageResult<T> {

    private PageParam pageParam;

    private T data;

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
