/* 
 * @(#)ContrabandServiceImpl.java
 *
 * Copyright 2018, 重庆华山智安科技有限公司保留.
 */
package com.unisinsight.framework.uuv.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unisinsight.framework.uuv.mapper.ContrabandMapper;
import com.unisinsight.framework.uuv.mapper.ContrabandTypeMapper;
import com.unisinsight.framework.uuv.model.ContrabandTypeDO;
import com.unisinsight.framework.uuv.model.ext.ContrabandExt;
import com.unisinsight.framework.uuv.service.ContrabandService;

/**
 * 违禁品接口实现
 * 
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 15:00
 * @since 1.0
 */
@Service
public class ContrabandServiceImpl implements ContrabandService {

	/** 静态变量：系统日志 */
	private static final Log logger = LogFactory
			.getLog(ContrabandServiceImpl.class);
	@Resource
	private ContrabandMapper contrabandMapper;
	@Resource
	private ContrabandTypeMapper contrabandTypeMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unisinsight.framework.uuv.service.ContrabandService#
	 * getContrabandExtByType(java.lang.String)
	 */
	@Override
	public List<ContrabandExt> getContrabandExtByType(String type)
			throws Exception {
		logger.info("type=" + type);
		if (StringUtils.isEmpty(type)) {
			throw new Exception("违禁品类型编码为空");
		}
		List<ContrabandExt> contrabandExts = contrabandMapper
				.getContrabandByType(type);
		return contrabandExts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unisinsight.framework.uuv.service.ContrabandService#getContrabandType
	 * ()
	 */
	@Override
	public List<ContrabandTypeDO> getContrabandType() throws Exception {
		List<ContrabandTypeDO> lsit = null;
		try {
			lsit = contrabandTypeMapper.getContrabandType();
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("获取违禁品类型失败", e);
			}
		}
		return lsit;
	}
}
