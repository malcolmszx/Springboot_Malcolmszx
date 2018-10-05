package com.kingdee.abc.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 多线程执行定时任务
 * @author 王久印
 * 2018年3月1日
 */
@Configuration
//所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
	
	
	/**
	 * 多线程执行异步任务
	 * @author malcolmszx
	 * 2018年10月1日
	 */
	@Bean(name = "taskExecutorWbswryxx")
	public Executor taskExecutorWbswryxx() {
	   ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	   executor.setCorePoolSize(10);
	   executor.setMaxPoolSize(10);
	   executor.setQueueCapacity(200);
	   executor.setKeepAliveSeconds(60);
	   executor.setThreadNamePrefix("taskExecutorWbswryxx-");
	   executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	   executor.setWaitForTasksToCompleteOnShutdown(true);
	   executor.setAwaitTerminationSeconds(60);
	    return executor;
	}

	


}
