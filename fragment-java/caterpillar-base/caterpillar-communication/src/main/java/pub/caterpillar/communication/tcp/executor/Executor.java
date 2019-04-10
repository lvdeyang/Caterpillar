/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.communication.executor.Executor
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Executor {

	static ExecutorService exec = Executors.newCachedThreadPool(
			new PriorityThreadFactory(2));
	static ScheduledExecutorService scheduledexec = Executors.newScheduledThreadPool(2,
			new PrefixThreafFactory("schedule_"));
	public static void exec(Runnable r){
		exec.execute(r);
	}
	public static void circleFixedDelayExec(Runnable command,long delay){
		scheduledexec.scheduleWithFixedDelay(command, 0, delay, TimeUnit.MILLISECONDS);
	}
	public static void circleAtRateExec(Runnable command,long delay){
		scheduledexec.scheduleAtFixedRate(command, 0, delay, TimeUnit.MILLISECONDS);
	}
	
	static class PriorityThreadFactory implements ThreadFactory {  
		Integer priority = Thread.NORM_PRIORITY;
		@Override
	    public Thread newThread(Runnable r) {  
	       Thread t = new Thread(r);  
	       t.setPriority(priority);  
	       return t;  
	    }  
		public PriorityThreadFactory(Integer priority){
			this.priority = priority;
		}
	}

	static class PrefixThreafFactory implements ThreadFactory {
	    final AtomicInteger id = new AtomicInteger(0);
	    private String prefix;

	    public PrefixThreafFactory(String prefix) {
	        this.prefix = prefix;
	    }
	    
	    @Override
		public Thread newThread(Runnable r) {
	        int i = id.incrementAndGet();
	        Thread t = new Thread(r, prefix + i);
	        t.setDaemon(true);
	        return t;
	    }
	}
}

