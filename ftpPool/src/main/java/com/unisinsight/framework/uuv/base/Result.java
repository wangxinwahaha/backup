package com.unisinsight.framework.uuv.base;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 *
 * @author wangxin [KF.wangxinA@h3c.com]
 * @date 2018/9/13 16:24
 * @since 1.0
 */
public class Result {

	/** 成员变量：消息编码*/
	private int code;
	/** 成员变量：消息内容 */
	private String message;
	/** 成员变量：返回数据*/
	private Object data;
	/** 成员变量：是否成功 */
	private boolean success;

	public Result() {
	}

	public Result setCode(ResultCode resultCode) {
		this.code = resultCode.getErrorCode();
		return this;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Result setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean getSuccess() {
		return success;
	}

	public Object getData() {
		return data;
	}

	public void success(Object data) {
		this.data = data;
		this.code = ResultCode.SUCCESS.getErrorCode();
		this.message = ResultCode.SUCCESS.name();
		this.success = true;
	}
	public void fail(String failMsg) {
		this.code = ResultCode.FAIL.getErrorCode();
		this.message = failMsg;
		this.success = false;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
