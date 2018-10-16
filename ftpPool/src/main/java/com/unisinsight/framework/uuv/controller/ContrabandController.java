/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisinsight.framework.uuv.base.Result;
import com.unisinsight.framework.uuv.model.ContrabandTypeDO;
import com.unisinsight.framework.uuv.model.ext.ContrabandExt;
import com.unisinsight.framework.uuv.service.ContrabandService;

/**
 * 组织Controller层
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/contrabands")
public class ContrabandController {
	/** 静态变量：系统日志 */
	private static final Log logger = LogFactory
			.getLog(ContrabandController.class);
	@Autowired
	private ContrabandService contrabandService;

	@GetMapping("/{type}")
	public Result getContrabands(@PathVariable(name = "type") String type) {
		Result result = new Result();
		List<ContrabandExt> contrabands;

		try {
			contrabands = contrabandService.getContrabandExtByType(type);
			result.success(contrabands);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("获取违禁品列表失败", e);
			}
		}
		return result;
	}
	
	@GetMapping("/type")
	public Result getContrabandType() {
		Result result = new Result();
		List<ContrabandTypeDO> contrabandTypes;
		try {
			contrabandTypes = contrabandService.getContrabandType();
			result.success(contrabandTypes);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("获取违禁品类型失败", e);
			}
		}
		return result;
	}
	
	


	
}
