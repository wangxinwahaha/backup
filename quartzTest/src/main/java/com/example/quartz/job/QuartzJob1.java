package com.example.quartz.job;

import java.util.Date;

import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * quzrtz任务配置类
 * 
 * @author wxyh
 */
@Component
@EnableScheduling
public class QuartzJob1 {

	public void task1() throws JobExecutionException {
		System.out.println(new Date());
	}
}
