/* 
 * @(#)ContrabandTypeDO.java
 *
 * Copyright 2018, 重庆华山智安科技有限公司保留.
 */
package com.unisinsight.framework.uuv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.ContrabandTypeDO;
import com.unisinsight.framework.uuv.model.ext.ContrabandExt;

/**
 * 违禁品类型mapper
 * 
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 10:41
 * @since 1.0
 */
public interface ContrabandTypeMapper extends Mapper<ContrabandTypeDO> {

	/**
	 * 获取未删除的违禁品类型
	 * 
	 * @return
	 */
	@Select("select * from contrabandtype where deleted=0")
	public List<ContrabandTypeDO> getContrabandType();

}