/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.dto.response.TitleResDTO;

import java.util.List;


/**
 * description 职级接口
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:08
 * @since 1.0
 */
public interface TitleService  {

    /**
    * 获取所有
    * @return
    */
    List<TitleResDTO> findAll();


}
