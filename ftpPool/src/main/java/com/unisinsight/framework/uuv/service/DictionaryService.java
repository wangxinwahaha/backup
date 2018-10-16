/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.unisinsight.framework.uuv.dto.request.DictionaryReqDTO;
import com.unisinsight.framework.uuv.dto.response.DictionaryResDTO;

import java.util.List;


/**
 * description 字典接口
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:13
 * @since 1.0
 */
public interface DictionaryService {

    /**
     * 新增
     *
     * @param reqDTO
     * @return
     */
    DictionaryResDTO save(DictionaryReqDTO reqDTO);


    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 更新
     *
     * @param updateDTO
     * @param id
     * @return
     */
    DictionaryResDTO update(DictionaryReqDTO updateDTO, Integer id);

    /**
     * 通过item查询key-value
     *
     * @param item  项目区分
     * @param appId 应用系统标识
     * @return key-value comments（备注）列表
     */
    List<DictionaryResDTO> selectDictionaryByItem(String item, Integer appId);

}
