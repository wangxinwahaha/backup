/* 
 * @(#)lkjsdbnf.java
 *
 * Copyright 2018, 迪爱斯通信设备有限公司保留.
 */
package com.unisinsight.framework.uuv.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.unisinsight.framework.uuv.common.config.FtpUtil;
import com.unisinsight.framework.uuv.common.utils.FtpServerUtil;
import com.unisinsight.framework.uuv.dto.request.SearchFaceReqDTO;

/**
 * 异步实现身份认证的文件读取
 *
 * @author wangxin [KF.wangxin@h3c.com]
 * @date 2018/9/6 15:11
 * @since 1.0
 */
@Service
public class IdentityAuthenticationService {

	/** 静态变量：系统日志 */
	private static final Log logger = LogFactory.getLog(IdentityAuthenticationService.class);

	@Resource
	private FtpServerUtil ftpServerUtil;

	@Async
	public void asyncIdentityAuthentication(String path, String fileName) {
		try {
			/*
			 * // 获取ftp连接 ftpClient = ftpServerUtil.getFtpClient(); // 进入文件夹
			 * ftpClient.changeWorkingDirectory(path); // 读取文件信息 String data =
			 * ftpServerUtil.readFile(ftpClient, fileName);
			 */
			String data = FtpUtil.readFile(fileName, path);
			SearchFaceReqDTO searchFaceReqDTO = JSON.parseObject(data, SearchFaceReqDTO.class);
			System.out.println(searchFaceReqDTO.getImage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
