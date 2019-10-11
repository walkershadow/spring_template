/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */

package com.scheduler.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author 陈旭
 * @version $Id: PoolThreadFactory, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月11日 13:54 陈旭 Exp $
 */

public class PoolThreadFactory implements ThreadFactory {

    /**
     * 线程计数器
     */
    private int counter;
    /**
     * 线程名称
     */
    private String name;


    public PoolThreadFactory(String name) {
        counter = 0;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable run) {
        Thread t = new Thread(run, name + "-Thread-" + counter);
        counter++;
        return t;
    }


}
