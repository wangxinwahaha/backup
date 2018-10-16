/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.base;

import com.github.pagehelper.Page;
import com.unisinsight.framework.uuv.dto.response.DictionaryResDTO;

import java.util.List;

/**
 * 分页相关
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class PageResultUtil {


    public static <T> PageResult<T> genPageResult(T list, Page page) {
        PageResult result = new PageResult();
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(page.getPageNum());
        pageParam.setPageSize(page.getPageSize());
        pageParam.setTotal((int)page.getTotal());
        result.setPageParam(pageParam);
        result.setData(list);
        return result;
    }

    public static <T> PageResult<T> genUserHaderPageResult(T list, Page page, List<DictionaryResDTO> header) {
        UserHeaderPageResult result = new UserHeaderPageResult();
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(page.getPageNum());
        pageParam.setPageSize(page.getPageSize());
        pageParam.setTotal((int)page.getTotal());
        result.setPageParam(pageParam);
        result.setData(list);
        result.setHeaders(header);
        return result;
    }

}
