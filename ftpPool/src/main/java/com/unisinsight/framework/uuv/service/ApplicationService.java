/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.unisinsight.framework.uuv.base.PageParam;
import com.unisinsight.framework.uuv.base.PageResult;
import com.unisinsight.framework.uuv.dto.request.ApplicationReqDTO;
import com.unisinsight.framework.uuv.dto.response.ApplicationResDTO;
import com.unisinsight.framework.uuv.dto.response.PrivilegeResDTO;

import java.util.List;
import java.util.Map;

/**
 * description 应用接口
 *
 * @author fengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:15
 * @since 1.0
 */
public interface ApplicationService {

    /**
     * 新增应用
     *
     * @param reqDTO
     * @return
     */
    ApplicationResDTO save(ApplicationReqDTO reqDTO);


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
     * @param appId
     * @return
     */
    ApplicationResDTO update(ApplicationReqDTO updateDTO, Integer appId);

    /**
     * 通过ID查找
     *
     * @param appId
     * @return
     */
    ApplicationResDTO getAppById(Integer appId);

    /**
     * 获取所有
     *
     * @return
     */
    List<ApplicationResDTO> findAll();

    /**
     * 获取权限列表
     *
     * @param appId
     * @param roleId
     * @param reversal
     * @param params
     * @param pageParam
     * @return
     */
    PageResult<List<PrivilegeResDTO>> getPrivilegeOrderList(Integer appId,
                                                            Integer roleId,
                                                            boolean reversal,
                                                            Map<String, Object> params,
                                                            PageParam pageParam);

    /**
     * 获取app排序清单
     *
     * @param params
     * @param pageIndex
     * @param pageSize
     * @param orderFiled
     * @param orderRule
     * @return
     */
    PageResult<List<ApplicationResDTO>> getAppOrderList(Map<String, Object> params,
                                                        Integer pageIndex,
                                                        Integer pageSize,
                                                        String orderFiled,
                                                        String orderRule);

}
