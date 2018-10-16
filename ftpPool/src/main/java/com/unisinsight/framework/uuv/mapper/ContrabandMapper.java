/* 
 * @(#)ContrabandTypeDO.java
 *
 * Copyright 2018, 重庆华山智安科技有限公司保留.
 */
package com.unisinsight.framework.uuv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.ContrabandDO;
import com.unisinsight.framework.uuv.model.ext.ContrabandExt;

/**
 * 违禁品实体类
 * 
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 10:41
 * @since 1.0
 */
public interface ContrabandMapper extends Mapper<ContrabandDO> {

	public List<ContrabandExt> getContrabandByType(@Param("type") String type);

}