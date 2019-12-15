package com.sumavision.tetris.outlink;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 计时器配置线程池文件<br/>
 * <b>作者:</b>SJJ<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月11日  
 */
public class ScheduleConfig implements SchedulingConfigurer{
	
	@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }
     
    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(5);
    }

}
