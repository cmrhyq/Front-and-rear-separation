package com.alan.blog.thread;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className TestThread.java
 * @project blog
 * @package com.alan.blog.thread
 * @date 2021/11/9-10:33
 * @email cmrhyq@163.com
 */
public class TestThread {

    public static void main(String args[]) {
        ThreadDemo r1 = new ThreadDemo( "Thread-1");
        r1.start();

        ThreadDemo r2 = new ThreadDemo( "Thread-2");
        r2.start();
    }
}
