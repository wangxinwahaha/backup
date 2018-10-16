package com.unisinsight.framework.uuv.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ftp工具类
 * 
 * @author: wangxin [KF.wangxinA@h3c.com]
 * @date: 2018/9/6 17:12
 * @see: FtpServerUtil
 * @since:1.0
 */
@Component
public class FtpServerUtil {

	@Value("${ftp.host}")
	private String host;

	@Value("${ftp.port}")
	private int port;

	@Value("${ftp.username}")
	private String username;

	@Value("${ftp.password}")
	private String password;

	@Value("${ftp.bufferSize}")
	private int bufferSize;


	/**
	 * Description: 获取ftpClient
	 *
	 * @return
	 */
	@SuppressWarnings("unused")
	public FTPClient getFtpClient() throws Exception {
		FTPClient ftpClient = new FTPClient();
		Exception ex = null;
		try {
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			ftpClient.setBufferSize(bufferSize);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
		} catch (Exception e) {
			ex = e;
			throw new Exception("Could not get a ftpClient", ex);
		}
		if (ftpClient == null) {
			throw new RuntimeException("Could not get a ftpClient", ex);
		}
		return ftpClient;
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
	public boolean uploadFile(String fileName, String content, String path) throws Exception {
		boolean success = false;
		// 获取ftp连接
		FTPClient ftp = this.getFtpClient();
		// 进入保存目录
		changeDirectory(path, ftp);
		InputStream inputStream = null;
		// OutputStream outputStream = null;
		// OutputStreamWriter outputStreamWriter = null;
		// PrintWriter writer = null;
		try {
			int reply;
			// 判断ftp是否连接成功
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 1.输入流
			inputStream = new ByteArrayInputStream(content.getBytes());
			ftp.storeFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1"), inputStream);
			// outputStream = ftp.appendFileStream(fileName);
			// outputStreamWriter = new OutputStreamWriter(outputStream,
			// "UTF-8");
			// writer = new PrintWriter(outputStreamWriter, true);
			// writer.write(content);
			// outputStream.flush();
			// outputStreamWriter.flush();
			// writer.flush();

			// FileWriter fileWriter=new FileWriter(fileName);
			// BufferedWriter bufferedWriter=new
			// BufferedWriter(outputStreamWriter);
			// bufferedWriter.write(content);
			ftp.logout();
			success = true;
		} catch (IOException e) {
			throw new Exception("身份认证信息ftp写入失败");
		} finally {
			inputStream.close();
			// writer.close();
			// outputStreamWriter.close();
			// outputStream.close();
			if (ftp.isConnected()) {
				try {
					// 关闭ftp连接
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
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
	public boolean changeDirectory(String dir, FTPClient ftp) {
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
	public void createDirectory(String path) throws Exception {
		FTPClient ftpClient = new FTPClient();
		Exception ex = null;
		try {
			// 获取ftp连接
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
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
			ftpClient.logout();
			ftpClient.disconnect();
		}
	}

	/**
	 * Description: 读取文件信息
	 *
	 * @param ftpClient
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String readFile(FTPClient ftpClient, String fileName) throws IOException {
		InputStream ins = null;
		StringBuilder builder = null;
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String line;
			builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
		return builder.toString();
	}

	/**
	 * Description: 编码路径
	 *
	 * @param path
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String encodingPath(String path) throws UnsupportedEncodingException {
		// FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
		return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
	}
}