package com.unisinsight.framework.uuv.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.unisinsight.framework.uuv.common.config.FtpUtil;
import com.unisinsight.framework.uuv.common.utils.FtpServerUtil;
import com.unisinsight.framework.uuv.service.IdentityAuthenticationService;

/**
 *
 * @Description:
 *
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018年10月15日
 *
 * @modified by:
 * @modified date:
 * @problem no:
 */

/**
 * 身份认证定时器
 * 
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018/10/15 8:44
 * @see: IdentityAuthenticationJob
 * @since:
 */
@Component
@EnableScheduling
public class IdentityAuthenticationJob {

	/**
	 * ftp身份认证服务文件路径
	 */
	@Value("${ftp.identity.authentication.service.path}")
	private String authenticationPath;

	/**
	 * ftp身份认证服务文件备份
	 */
	@Value("${ftp.identity.authentication.backup.path}")
	private String authenticationbackup;

	@Resource
	private FtpServerUtil ftpServerUtil;

	@Resource
	private IdentityAuthenticationService identityAuthenticationService;

	@Scheduled(cron = "0/10 * * * * ?")
	// 每10秒钟执行一次
	public void downloadFile() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 备份目录路径
		String backupPath = "/" + authenticationbackup + "/" + dateFormat.format(new Date());
		/*
		 * 获取ftp连接,同时创建需要建的目录 FTPClient ftp = ftpServerUtil.getFtpClient();
		 */
		// 使用ftp池获取连接
		FTPClient ftp = FtpUtil.getFtpClient();
		// 进入到身份认证文本信息目录
		ftp.changeWorkingDirectory("/" + authenticationPath);
		try {
			FTPFile[] directories = ftp.listDirectories();
			if (directories == null) {
				return;
			}
			for (FTPFile directory : directories) {
				if (!".".equals(directory.getName()) && !"..".equals(directory.getName())) {
					// 进入按照日期存储的文本目录
					ftp.changeWorkingDirectory(directory.getName());
					FTPFile[] files = ftp.listFiles();
					for (FTPFile ftpFile : files) {
						if (!".".equals(ftpFile.getName()) && !"..".equals(ftpFile.getName())) {
							FtpUtil.createDirectory(backupPath);
							// 如果文件正在被编辑，移动失败，则继续下一次
							Boolean success = ftp.rename(ftpFile.getName(), "/" + backupPath + "/" + ftpFile.getName());
							if (success) {
								// 进入到ftp备份目录读取文本信息，进入下一步操作
								identityAuthenticationService.asyncIdentityAuthentication(backupPath, ftpFile.getName());
							}
						}
					}
					ftp.changeToParentDirectory();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭ftp连接
			// ftp.logout();
			// ftp.disconnect();
			FtpUtil.releaseFtpClient(ftp);
		}
	}
}
