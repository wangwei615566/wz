package com.pos.api.util;

import com.wz.cashloan.core.common.util.HttpsUtil;

import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ItemQueueThread {

    private ItemQueueThread() {
    }

    /**
     * 延迟加载(线程安全)
     */
    private static class LazyHolder {
        private static ItemQueueThread itemQueueThread = new ItemQueueThread();
    }

    public static ItemQueueThread getInstance() {
        return LazyHolder.itemQueueThread;
    }

    /**
     * 缓存线程池
     */
    ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * 线程
     */
    private Thread daemonThread;

    /**
     * 初始化线程
     */
    public void init() {
        /*daemonThread = new Thread(() -> {
            try
            {
                execute();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                logger.info(e.getMessage());
            }
        });*/
        daemonThread = new Thread() {

            @Override
            public void run() {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        daemonThread.start();
    }

    private void execute()
            throws InterruptedException {
        while (true) {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            try {
                // 从延迟队列中取值,如果没有对象过期则队列一直等待，
                DelayItem<?> t1 = item.take();
                if (t1 != null) {
                    Runnable task = t1.getTask();
                    if (task == null) {
                        continue;
                    }
                    executor.execute(task);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建空的延迟队列
     */
    private DelayQueue<DelayItem<?>> item = new DelayQueue<DelayItem<?>>();

    /**
     * 往队列中添加任务
     *
     * @param time     延迟时间
     * @param task     任务
     * @param timeUnit 时间单位
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void put(long time, Runnable task, TimeUnit timeUnit) {
        // 转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        DelayItem<?> k = new DelayItem(nanoTime, task);
        item.put(k);
    }

    /**
     * 结束任务
     *
     * @param task
     */
    public boolean endTask(DelayItem<Runnable> task) {
        return item.remove(task);
    }


}
