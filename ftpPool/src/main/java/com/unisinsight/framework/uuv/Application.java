/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * description
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:06
 * @since 1.0
 */
@EnableAsync
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
