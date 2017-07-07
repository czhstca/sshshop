package com.chipmore.shop.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJobTest implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 定义任务具体的实现
		System.out.println("我有一只小毛驴我从来也不骑~~" + new Date().toLocaleString());
		
	}

}
