package com.leetcode.java.concurrency.a1116_print_zero_even_odd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * title: 打印零与奇偶数
 * You have a function printNumber that can be called with an integer parameter and prints it to the console.
 * For example, calling printNumber(7) prints 7 to the console.
 * You are given an instance of the class ZeroEvenOdd that has three functions: zero, even, and odd. The same instance of ZeroEvenOdd will be passed to three different threads:
 * Thread A: calls zero() that should only output 0's.
 * Thread B: calls even() that should only output even numbers.
 * Thread C: calls odd() that should only output odd numbers.
 * Modify the given class to output the series "010203040506..." where the length of the series must be 2n.
 * Implement the ZeroEvenOdd class:
 * ZeroEvenOdd(int n) Initializes the object with the number n that represents the numbers that should be printed.
 * void zero(printNumber) Calls printNumber to output one zero.
 * void even(printNumber) Calls printNumber to output one even number.
 * void odd(printNumber) Calls printNumber to output one odd number.
 * <p>
 * <p>
 * Example 1:
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously.
 * One of them calls zero(), the other calls even(), and the last one calls odd().
 * "0102" is the correct output.
 * <p>
 * Example 2:
 * Input: n = 5
 * Output: "0102030405"
 * <p>
 * <p>
 * Constraints:
 * 1 <= n <= 1000
 */

/**
 * 方法一：计数信号量Semaphore
 */
class ZeroEvenOdd {
    private int n;
    private Semaphore zeroSema = new Semaphore(1);
    private Semaphore evenSema = new Semaphore(0);
    private Semaphore oddSema = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroSema.acquire();
            printNumber.accept(0);
            if ((i & 1) == 1) {
                oddSema.release();
            } else {
                evenSema.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSema.acquire();
            printNumber.accept(i);
            zeroSema.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSema.acquire();
            printNumber.accept(i);
            zeroSema.release();
        }
    }
}

/**
 * 方法二：Thread.yield() + volatile
 */
class ZeroEvenOdd2 {
    private int n;
    //一个线程修改完主内存中的state后，会立即通知其它线程
    private volatile int state;

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            state = (i & 1) == 1 ? 1 : 2;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                Thread.yield();
            }
            printNumber.accept(i);
            state = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            state = 0;
        }
    }
}
