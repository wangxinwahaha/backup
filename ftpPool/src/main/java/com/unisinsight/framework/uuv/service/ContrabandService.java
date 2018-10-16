/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;

import com.unisinsight.framework.uuv.model.ContrabandTypeDO;
import com.unisinsight.framework.uuv.model.ext.ContrabandExt;

import java.util.List;

/**
 * 违禁品信息接口
 * 
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 14:57
 * @since 1.0
 */
public interface ContrabandService {

	/**
	 * 获取违禁品信息
	 * 
	 * @param type
	 *            违禁品类型
	 * @return
	 */
	public List<ContrabandExt> getContrabandExtByType(String type)
			throws Exception;

	/**
	 * 获取违禁品类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ContrabandTypeDO> getContrabandType() throws Exception;
}
