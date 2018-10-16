package com.unisinsight.framework.uuv.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.unisinsight.framework.uuv.base.Result;
import com.unisinsight.framework.uuv.base.ResultGenerator;
import com.unisinsight.framework.uuv.common.config.FtpUtil;
import com.unisinsight.framework.uuv.common.utils.FtpServerUtil;
import com.unisinsight.framework.uuv.dto.request.SearchFaceReqDTO;

/**
 * 身份认证Controller
 * 
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018/10/11 14:14
 * @see: IdentityAuthenticationController
 * @since:1.0
 */

@RestController
@RequestMapping("/identity")
public class IdentityAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(FtpServerUtil.class);

	@Resource
	private FtpServerUtil ftpServerUtil;

	/**
	 * 身份认证类型（1：图片 0：身份证号码）
	 */
	private final static String TYPE = "0";

	/**
	 * ftp身份认证服务文件路径
	 */
	@Value("${ftp.identity.authentication.service.path}")
	private String authenticationPath;

	/**
	 * Description: 身份认证接口
	 *
	 * @param searchFaceReqDTO
	 */
	@PostMapping("/authentication")
	@ApiOperation(value = "身份认证", notes = "身份认证接口")
	@ApiImplicitParam(name = "searchFaceReqDTO", value = "搜索请求参数", required = true, dataType = "SearchFaceReqDTO")
	public void identityAuthentication(@RequestBody @Valid SearchFaceReqDTO searchFaceReqDTO) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 通过身份证号码认证，判断身份证号码是否为空
		if (TYPE.equals(searchFaceReqDTO.getType()) && StringUtils.isBlank(searchFaceReqDTO.getIdCard())) {
			Assert.notNull(searchFaceReqDTO.getIdCard(), "身份证号码不能为空");
		}
		if (!TYPE.equals(searchFaceReqDTO.getType()) && StringUtils.isBlank(searchFaceReqDTO.getImage())) {
			Assert.notNull(searchFaceReqDTO.getImage(), "图像信息不能为空");
		}
		// 转换为字符串数据
		String content = JSON.toJSONString(searchFaceReqDTO);
		// 获取文件名称
		String fileName = searchFaceReqDTO.getClass().getSimpleName() + new Date().getTime() + ".json";
		Boolean success = null;
		// 获取文件存储路径
		String path = authenticationPath + "/" + dateFormat.format(new Date());
		try {
//			success = ftpServerUtil.uploadFile(fileName, content, path);
			success = FtpUtil.uploadFile(fileName, content, path);
			if (!success) {
				logger.info("身份认证信息发送失败");
			}
		} catch (Exception e) {
			logger.error("身份认证失败", e);

		}

	}
}
