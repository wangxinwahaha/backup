/* 
 * @(#)ContrabandTypeDO.java
 *
 * Copyright 2018, 重庆华山智安科技有限公司保留.
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 违禁品类型实体类
 * 
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 10:41
 * @since 1.0
 */
@Table(name = "contrabandtype")
public class ContrabandTypeDO {

	/** 成员变量：id */
	@Id
	@Column(name = "id")
	private Integer id;

	/** 成员变量：名称 */
	@Column(name = "name")
	private String name;

	/** 成员变量：类型编码 */
	@Column(name = "typeCoding")
	private String typeCoding;

	/** 成员变量：描述 */
	@Column(name = "description")
	private String description;

	/** 成员变量：删除标志 -1：删除 0：未删除 */
	@Column(name = "deleted")
	private Integer deleted = 0;

	/**
	 * 获取类成员id
	 * 
	 * @return {@link #id}
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设定类成员id
	 * 
	 * @param id
	 *            要设定的{@link #id}
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取类成员name
	 * 
	 * @return {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设定类成员name
	 * 
	 * @param name
	 *            要设定的{@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取类成员typeCoding
	 * 
	 * @return {@link #typeCoding}
	 */
	public String getTypeCoding() {
		return this.typeCoding;
	}

	/**
	 * 设定类成员typeCoding
	 * 
	 * @param typeCoding
	 *            要设定的{@link #typeCoding}
	 */
	public void setTypeCoding(String typeCoding) {
		this.typeCoding = typeCoding;
	}

	/**
	 * 获取类成员description
	 * 
	 * @return {@link #description}
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设定类成员description
	 * 
	 * @param description
	 *            要设定的{@link #description}
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取类成员deleted
	 * 
	 * @return {@link #deleted}
	 */
	public Integer getDeleted() {
		return this.deleted;
	}

	/**
	 * 设定类成员deleted
	 * 
	 * @param deleted
	 *            要设定的{@link #deleted}
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
