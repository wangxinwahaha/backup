/* 
 * @(#)ContrabandExt.java
 *
 * Copyright 2018, 重庆华山智安科技有限公司保留.
 */
package com.unisinsight.framework.uuv.model.ext;

import com.unisinsight.framework.uuv.model.ContrabandDO;

/**
 * 违禁品实体详细信息类
 * 
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 10:41
 * @since 1.0
 */
public class ContrabandExt extends ContrabandDO {

	/** 成员变量：违禁品类型 */
	private String contrabandType;

	/** 成员变量：违禁品类型名称 */
	private String contrabandTypeName;

	/**
	 * 获取类成员contrabandType
	 * 
	 * @return {@link #contrabandType}
	 */
	public String getContrabandType() {
		return this.contrabandType;
	}

	/**
	 * 设定类成员contrabandType
	 * 
	 * @param contrabandType
	 *            要设定的{@link #contrabandType}
	 */
	public void setContrabandType(String contrabandType) {
		this.contrabandType = contrabandType;
	}

	/**
	 * 获取类成员contrabandTypeName
	 * 
	 * @return {@link #contrabandTypeName}
	 */
	public String getContrabandTypeName() {
		return this.contrabandTypeName;
	}

	/**
	 * 设定类成员contrabandTypeName
	 * 
	 * @param contrabandTypeName
	 *            要设定的{@link #contrabandTypeName}
	 */
	public void setContrabandTypeName(String contrabandTypeName) {
		this.contrabandTypeName = contrabandTypeName;
	}

}
