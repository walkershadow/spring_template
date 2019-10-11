/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */

package com.scheduler.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @author 陈旭
 * @version $Id: ThreadPool, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月11日 11:41 陈旭 Exp $
 */

public class ThreadPool {

    /**
     * 创建ThreadPoolExecutor线程池对象，并初始化该对象的各种参数
     */
    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadPool(String threadFactoryName) {
        //设置核心池大小
        int corePoolSize = 30;
        //设置线程池最大能接受多少线程
        int maximumPoolSize = 30;
        //当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
        long keepActiveTime = 0L;
        //设置时间单位，毫秒
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        //设置线程池缓存队列的排队策略
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
        //线程工厂
        ThreadFactory threadFactory = new PoolThreadFactory(threadFactoryName);
        //创建线程池
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit, workQueue, threadFactory);
    }



    public void execute(final Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public boolean isShutdown() {
        return threadPoolExecutor.isShutdown();
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

}
