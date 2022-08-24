package com.leetcode.java.concurrency.a1115_print_foobar_alternately;

import java.util.concurrent.Semaphore;

/**
 * title: 交替打印 FooBar
 * Suppose you are given the following code:
 * class FooBar {
 * public void foo() {
 * for (int i = 0; i < n; i++) {
 * print("foo");
 * }
 * }
 * <p>
 * public void bar() {
 * for (int i = 0; i < n; i++) {
 * print("bar");
 * }
 * }
 * }
 * The same instance of FooBar will be passed to two different threads:
 * thread A will call foo(), while
 * thread B will call bar().
 * Modify the given program to output "foobar" n times.
 * <p>
 * <p>
 * Example 1:
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
 * "foobar" is being output 1 time.
 * <p>
 * Example 2:
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 * <p>
 * <p>
 * Constraints:
 * 1 <= n <= 1000
 */

/**
 * 方法一：计数信号量Semaphore
 */
class FooBar {
    private int n;
    private Semaphore fooSema = new Semaphore(1);
    private Semaphore barSema = new Semaphore(0);


    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //值大于0时，能获取到许可证，值减1，并执行下面的操作
            fooSema.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            //释放许可证给barSema这个信号量，因此barSema的值+1
            barSema.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //值大于0时，能获取到许可证，值减1，并执行下面的操作
            barSema.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            //释放许可证给fooSema这个信号量，因此fooSema的值+1
            fooSema.release();
        }
    }
}

/**
 * 方法二：synchronized + volatile
 */
class FooBar2 {
    private int n;
    // 标志位，控制执行顺序：true执行printFoo；false执行printBar。
    private volatile boolean flag = true;
    // 锁对象
    private final Object obj = new Object();

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (obj) {
                //循环判断，防止虚假唤醒。不能用if
                while (!flag) {
                    obj.wait();
                }
                //干活
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                //通知
                obj.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (obj) {
                //循环判断，防止虚假唤醒。不能用if
                while (flag) {
                    obj.wait();
                }
                //干活
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
                //通知
                obj.notifyAll();
            }
        }
    }
}

/**
 * 方法三：Thread.yield() + volatile
 */
class FooBar3 {
    private int n;
    private volatile int state;

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printFoo.run();
            state = 1;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (state != 1) {
                Thread.yield();
            }
            printBar.run();
            state = 0;
        }
    }
}
