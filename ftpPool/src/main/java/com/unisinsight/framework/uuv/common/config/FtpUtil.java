package com.unisinsight.framework.uuv.common.config;

/**
 *
 * @Description:
 *
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018年10月11日
 *
 * @modified by:
 * @modified date:
 * @problem no:
 */

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018年10月11日
 * @see: TtpUtil
 * @since:
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Ftp 工具类
 * 
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018/10/11 17:12
 * @since:1.0
 */
@Component
public class FtpUtil {
	private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

	/**
	 * ftpClient连接池初始化标志
	 */
	private static volatile boolean hasInit = false;

	/**
	 * ftpClient连接池
	 */
	private static ObjectPool<FTPClient> ftpClientPool;

	/**
	 * 初始化ftpClientPool
	 *
	 * @param ftpClientPool
	 */
	public static void initFtpClientPool(ObjectPool<FTPClient> ftpClientPool) {
		if (!hasInit) {
			synchronized (FtpUtil.class) {
				if (!hasInit) {
					FtpUtil.ftpClientPool = ftpClientPool;
					hasInit = true;
				}
			}
		}
	}

	/**
	 * Description: 上传文本信息到ftp
	 *
	 * @param fileName
	 *            文件名称包括扩展名
	 * @param content
	 *            内容
	 * @param path
	 *            保存路径
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFile(String fileName, String content, String path) throws Exception {
		boolean success = false;
		// 获取ftp连接
		FTPClient ftp = getFtpClient();
		// 进入保存目录
		changeDirectory(path, ftp);

		InputStream inputStream = null;
		try {
			int reply;
			// 判断ftp是否连接成功
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			// 1.输入流
			inputStream = new ByteArrayInputStream(content.getBytes());
			ftp.storeFile(encodingPath(fileName), inputStream);
			// ftp.logout();
			success = true;
		} catch (IOException e) {
			throw new Exception("身份认证信息ftp写入失败");
		} finally {
			inputStream.close();
			releaseFtpClient(ftp);
			// if (ftp.isConnected()) {
			// try {
			// // 关闭ftp连接
			// ftp.disconnect();
			// } catch (IOException ioe) {
			// }
			// }
		}
		return success;
	}

	/**
	 * Description: 切换目录(有则切换目录，没有则创建目录,在切换)
	 *
	 * @param dir
	 * @param ftp
	 * @return
	 */
	public static boolean changeDirectory(String dir, FTPClient ftp) {
		String directory;
		try {
			// 目录编码，解决中文路径问题
			directory = encodingPath(dir);
			// 尝试切入目录
			if (ftp.changeWorkingDirectory(directory))
				return true;
			String[] arr = dir.split("/");
			StringBuffer sbfDir = new StringBuffer();
			// 循环生成子目录
			for (String s : arr) {
				sbfDir.append("/");
				sbfDir.append(s);
				// 目录编码，解决中文路径问题
				directory = encodingPath(sbfDir.toString());
				// 尝试切入目录
				if (ftp.changeWorkingDirectory(directory))
					continue;
				if (!ftp.makeDirectory(directory)) {
					return false;
				}
			}
			// 将目录切换至指定路径
			return ftp.changeWorkingDirectory(directory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Description: 获取ftp连接后创建目录,若存在则返回
	 *
	 * @param path
	 *            目录路径(例:authentication/2018-10-8)
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void createDirectory(String path) throws Exception {
		FTPClient ftpClient = null;
		Exception ex = null;
		try {
			// 获取ftp连接
			ftpClient = getFtpClient();
			// 创建目录
			String directory;
			// 目录编码，解决中文路径问题
			directory = encodingPath(path);
			// 尝试切入目录,能进入则关闭连接，返回
			if (ftpClient.changeWorkingDirectory(directory)) {
				return;
			}
			String[] arrs = path.split("/");
			StringBuffer sbfDir = new StringBuffer();
			// 循环生成子目录
			for (String dir : arrs) {
				sbfDir.append("/");
				sbfDir.append(dir);
				// 目录编码，解决中文路径问题
				directory = encodingPath(sbfDir.toString());
				// 尝试切入目录
				if (ftpClient.changeWorkingDirectory(directory))
					continue;
				if (!ftpClient.makeDirectory(directory)) {
					throw new Exception("ftp目录创建失败");
				}
			}
		} catch (Exception e) {
			throw new Exception("ftp目录创建失败", e);
		} finally {
			releaseFtpClient(ftpClient);
		}
	}

	/**
	 * Description: 读取ftp上文件信息
	 *
	 * @param fileName
	 *            文件名
	 * @param path
	 *            路径
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String fileName, String path) throws IOException {
		InputStream ins = null;
		StringBuilder builder = null;
		BufferedReader reader = null;
		FTPClient ftpClient = getFtpClient();
		ftpClient.changeWorkingDirectory(path);
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(encodingPath(fileName));
			reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String line;
			builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			logger.error("文件读取失败" + fileName);
			throw new IOException("文件读取失败");
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (ins != null) {
				ins.close();
				try {
					ftpClient.completePendingCommand();
				} catch (Exception e) {
					if (ftpClient.isAvailable())
						ftpClient.disconnect();
				}
			}
			releaseFtpClient(ftpClient);
		}
		return builder.toString();
	}

	/**
	 * 获取ftpClient
	 *
	 * @return
	 */
	public static FTPClient getFtpClient() {
		checkFtpClientPoolAvailable();
		FTPClient ftpClient = null;
		Exception ex = null;
		// 获取连接最多尝试3次
		for (int i = 0; i < 3; i++) {
			try {
				ftpClient = ftpClientPool.borrowObject();
				break;
			} catch (Exception e) {
				ex = e;
			}
		}
		if (ftpClient == null) {
			throw new RuntimeException("Could not get a ftpClient from the pool", ex);
		}
		return ftpClient;
	}

	/**
	 * 编码文件路径
	 */
	private static String encodingPath(String path) throws UnsupportedEncodingException {
		// FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
		return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
	}

	/**
	 * Description: 释放ftpClient，退回根目录
	 *
	 * @param ftpClient
	 */
	public static void releaseFtpClient(FTPClient ftpClient) {
		if (ftpClient == null) {
			return;
		}
		try {
			// 退回根目录
			changeToRootDirectory(ftpClient);
			ftpClientPool.returnObject(ftpClient);
		} catch (Exception e) {
			logger.error("ftp连接释放失败");
			if (ftpClient.isAvailable()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e1) {
					logger.error("ftp连接关闭失败");
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Description: 检查ftpClientPool是否可用
	 *
	 */
	private static void checkFtpClientPoolAvailable() {
		Assert.state(hasInit, "FTP未启用或连接失败！");
	}

	/**
	 * Description: 递归返回根目录
	 *
	 * @param ftpClient
	 * @throws IOException
	 */
	private static void changeToRootDirectory(FTPClient ftpClient) throws IOException {
		// 判断目录是否在根目录
		if (ftpClient.printWorkingDirectory() != null && !"/".equals(ftpClient.printWorkingDirectory())) {
			ftpClient.changeToParentDirectory();
			changeToRootDirectory(ftpClient);
		}
	}
}
