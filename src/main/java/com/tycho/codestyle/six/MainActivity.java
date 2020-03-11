package com.tycho.codestyle.six;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 六-12 【强制】多线程并行处理定时任务时， Timer 运行多个 TimeTask 时，只要其中之一没有捕获
抛出的异常，其它任务便会自动终止运行，如果在处理定时任务时使用
ScheduledExecutorService 则没有这个问题
 *
 */
public class MainActivity{
	 
    public void test() {
 
    	ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run "+ System.currentTimeMillis());
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
 
 
    }
}
