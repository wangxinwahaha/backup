/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.unisinsight.framework.uuv.dto.request.PrivilegeReqDTO;
import com.unisinsight.framework.uuv.dto.response.PrivilegeResDTO;
import java.util.List;

/**
 * description
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:11
 * @since 1.0
 */
public interface PrivilegeService {

    /**
     * 新增
     * @param reqDTO
     * @param appId
     * @return
     */
    PrivilegeResDTO save(PrivilegeReqDTO reqDTO, Integer appId);


    /**
     * 删除
     * @param appId
     * @param privilegeIds
     * @return
     */
    void deleteById(Integer appId, List<Integer> privilegeIds);

    /**
     * 更新
     * @param updateDTO
     * @param appId
     * @param privilegeId
     * @return
     */
    PrivilegeResDTO update(PrivilegeReqDTO updateDTO, Integer appId, Integer privilegeId);
}
