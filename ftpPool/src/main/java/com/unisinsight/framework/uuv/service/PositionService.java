/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.dto.response.PositionResDTO;

import java.util.List;

/**
 * description 岗位接口
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:11
 * @since 1.0
 */
public interface PositionService  {
    /**
    * 获取所有
    * @return
    */
    List<PositionResDTO> findAll();


}
