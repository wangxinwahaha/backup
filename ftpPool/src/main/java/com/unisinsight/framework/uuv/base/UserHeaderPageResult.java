/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.base;

import com.unisinsight.framework.uuv.dto.response.DictionaryResDTO;

import java.util.List;

/**
 * 分页相关
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class UserHeaderPageResult <T> extends PageResult<T> {
    private List<DictionaryResDTO> headers;

    public void setHeaders(List<DictionaryResDTO> headers) {
        this.headers = headers;
    }

    public List<DictionaryResDTO> getHeaders() {
        return headers;
    }
}
