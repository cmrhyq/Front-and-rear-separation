package com.alan.blog.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className RunnableThreadDemo.java
 * @project blog
 * @package com.alan.blog.thread
 * @date 2021/11/9-10:37
 * @email cmrhyq@163.com
 */
@Slf4j
public class ThreadDemo extends Thread {

    private Thread t;
    private String threadName;


    ThreadDemo(String name) {
        threadName = name;
        log.info("创建线程：" + threadName);
    }

    @Override
    public void run() {
        log.info("运行线程：" + threadName);
        try{
            for(int i = 4; i > 0; i--) {
                log.info("线程: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e){
            log.info("线程："+threadName+"中断");
        }
        log.info("线程： " +  threadName + " 退出.");
    }

    @Override
    public void start () {
        log.info("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
