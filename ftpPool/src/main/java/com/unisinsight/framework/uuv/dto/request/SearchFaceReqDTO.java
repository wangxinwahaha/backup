/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */

package com.unisinsight.framework.uuv.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 人脸识别DTO
 * 
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018/10/4 18:06
 * @see: SearchFaceReqDTO
 * @since:
 */

public class SearchFaceReqDTO implements Serializable {

	private static final long serialVersionUID = -499161873952046339L;

	/**
	 * 相似度
	 */
	private Float minScore;

	/**
	 * 传入的图片base64
	 */
	private String image;

	/**
	 * 身份证号码
	 */
	private String idCard;

	/**
	 * 身份认证类型（1：图片 0：身份证号码）
	 */
	@NotNull(message = "身份证号码不能为空")
	private String type;

	/**
	 * 电话号码
	 */
	@NotNull(message = "电话号码不能为空")
	private String phone;

	/**
	 * @return returns the minScore
	 */
	public Float getMinScore() {
		return minScore;
	}

	/**
	 * @param minScore
	 *            the minScore to set
	 */
	public void setMinScore(Float minScore) {
		this.minScore = minScore;
	}

	/**
	 * @return returns the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return returns the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard
	 *            the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return returns the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return returns the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchFaceReqDTO [minScore=" + minScore + ", image=" + image + ", idCard=" + idCard + ", type=" + type + ", phone=" + phone + "]";
	}

}