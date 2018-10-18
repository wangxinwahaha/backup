package com.example.quartz.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.example.quartz.job.QuartzJob1;

/**
 * quzrtz任务配置类
 * 
 * @author wxyh
 */
@Configuration
public class QuartzConfiguration {

	// 配置定时任务1
	@Bean(name = "firstJobDetail")
	public MethodInvokingJobDetailFactoryBean firstJobDetail(QuartzJob1 firstJob) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		// 是否并发执行
		jobDetail.setConcurrent(false);
		// 为需要执行的实体类对应的对象
		jobDetail.setTargetObject(firstJob);
		// 需要执行的方法
		jobDetail.setTargetMethod("task1");
		return jobDetail;
	}

	// 配置触发器1
	@Bean(name = "firstTrigger")
	public CronTriggerFactoryBean firstTrigger(JobDetail firstJobDetail) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(firstJobDetail);
		trigger.setCronExpression("0/10 * * * * ?");
		return trigger;
	}

	// 配置Scheduler
	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactory(Trigger firstTrigger) {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		// 延时启动，应用启动1秒后
		bean.setStartupDelay(1);
		// 注册触发器
		bean.setTriggers(firstTrigger);
		return bean;
	}
}
