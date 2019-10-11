package com.scheduler.timer;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import com.scheduler.thread.PoolThreadFactory;
import com.scheduler.thread.ThreadPoolFactory;
import com.scheduler.thread.task.Task;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 延时执行器
 *
 * @author 陈旭
 * @version $Id: InitTimer, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月24日 14:26 陈旭 Exp $
 */
public class DelayTimer {

    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(30, new PoolThreadFactory("delayer"));

    /**
     * 延时处理线程任务
     * @param runnable
     * @param seconds
     */
    public static void execute( final Runnable runnable, int seconds) {
        // 延时任务
        scheduledExecutorService.schedule(runnable, seconds, TimeUnit.SECONDS);
    }


}
