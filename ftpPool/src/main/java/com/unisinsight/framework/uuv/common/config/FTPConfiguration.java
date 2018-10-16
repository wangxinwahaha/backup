package com.unisinsight.framework.uuv.common.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * FTP配置类
 * 
 * @author wxyh
 */
@Component
// @ConditionalOnClass({ GenericObjectPool.class, FTPClient.class })
// @EnableConfigurationProperties(FTPConfiguration.FtpConfigProperties.class)
public class FTPConfiguration {

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

	@Value("${ftp.initialSize}")
	private Integer initialSize;

	private ObjectPool<FTPClient> pool;

	/**
	 * Description: 初始化ftp连接池
	 *
	 */
	@PostConstruct
	public void initFtpConfiguration() {
		// 默认最大连接数与最大空闲连接数都为8，最小空闲连接数为0
		// 其他未设置属性使用默认值，可根据需要添加相关配置
		@SuppressWarnings("rawtypes")
		/** 使用默认配置 */
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		// 向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取。默认为false。建议保持默认值.
		// poolConfig.setTestOnBorrow(true);
		// 向连接池“归还”链接时，是否检测“链接”对象的有效性。默认为false。建议保持默认值.
		// poolConfig.setTestOnReturn(true);
		// 向调用者输出“链接”对象时，是否检测它的空闲超时；默认为false。如果“链接”空闲超时，将会被移除。建议保持默认值.
		// poolConfig.setTestWhileIdle(true);
		// 连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除
		// poolConfig.setMinEvictableIdleTimeMillis(60000);
		// 连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留“minIdle”个空闲连接数。默认为-1.
		// poolConfig.setSoftMinEvictableIdleTimeMillis(50000);
		// “空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1
		// poolConfig.setTimeBetweenEvictionRunsMillis(30000);
		// 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true;
		// poolConfig.setBlockWhenExhausted(false);
		// 最小空闲连接数
		// poolConfig.setMaxIdle(1);
		pool = new GenericObjectPool<>(new FtpClientPooledObjectFactory(), poolConfig);
		// maxIdle属性是指pool中最多能保留多少个空闲对象。这个属性主要是在returnObject函数中使用的。在程序使用完相关的对象后，会调用returnObject函数将对象返回到pool中。
		// 但是如果当前maxIdleSave
		// <=idleObjects.size()，即当前pool中空闲对象的数量大于等于maxIdle时候，直接调用destroy函数来销毁对象
		// this.preLoadingFtpClient(initialSize, poolConfig.getMaxIdle());
		// 初始化ftp工具类中的ftpClientPool
		FtpUtil.initFtpClientPool(pool);
	}

	/**
	 * 预先加载FTPClient连接到对象池中
	 * 
	 * @param initialSize
	 *            初始化连接数
	 * @param maxIdle
	 *            最大空闲连接数
	 */
	private void preLoadingFtpClient(Integer initialSize, int maxIdle) {
		if (initialSize == null || initialSize <= 0) {
			return;
		}

		int size = Math.min(initialSize.intValue(), maxIdle);
		for (int i = 0; i < size; i++) {
			try {
				pool.addObject();
			} catch (Exception e) {
			}
		}
	}

	@PreDestroy
	public void destroy() {
		if (pool != null) {
			pool.close();
		}
	}

	/**
	 * FtpClient对象工厂类
	 */
	class FtpClientPooledObjectFactory implements PooledObjectFactory<FTPClient> {

		@Override
		public PooledObject<FTPClient> makeObject() throws Exception {
			FTPClient ftpClient = new FTPClient();
			try {
				ftpClient.connect(host, port);
				ftpClient.login(username, password);
				ftpClient.setBufferSize(bufferSize);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				return new DefaultPooledObject<>(ftpClient);
			} catch (Exception e) {
				if (ftpClient.isAvailable()) {
					ftpClient.disconnect();
				}
				ftpClient = null;
				throw new Exception("建立FTP连接失败", e);
			}
		}

		@Override
		public void destroyObject(PooledObject<FTPClient> poolFtpClient) throws Exception {
			FTPClient ftpClient = poolFtpClient.getObject();
			ftpClient.logout();
			if (ftpClient != null && ftpClient.isConnected()) {
				ftpClient.disconnect();
			}
		}

		@Override
		public boolean validateObject(PooledObject<FTPClient> poolFtpClient) {
			FTPClient ftpClient = poolFtpClient.getObject();
			if (ftpClient == null || !ftpClient.isConnected()) {
				return false;
			}
			try {
				ftpClient.changeWorkingDirectory("/");
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * Description: 重新初始化由池返回的实例
		 *
		 */
		@Override
		public void activateObject(PooledObject<FTPClient> poolFtpClient) throws Exception {
			FTPClient ftpClient = poolFtpClient.getObject();
			ftpClient.isAvailable();
		}

		@Override
		public void passivateObject(PooledObject<FTPClient> p) throws Exception {
		}

	}

}
