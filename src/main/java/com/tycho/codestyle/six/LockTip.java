package com.tycho.codestyle.six;

import java.util.Date;
/**
 * 六-7 【强制】高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁 ；
能锁区块，就不要锁整个方法体 ； 能用对象锁，就不要用类锁。
说明：尽可能使加锁的代码块工作量尽可能的小，避免在锁代码块中调用 RPC 方法。
 *
 */
public class LockTip {
	public synchronized void run() {
        Date startDate = new Date();
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
                System.out.println("开始时间 ：" + startDate + " 当前时间 ：" + new Date());
                System.out.println();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	public void run2() {
        Date startDate = new Date();
        int counter = 0;
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
                    System.out.println("开始时间 ：" + startDate + " 当前时间 ：" + new Date());
                    System.out.println();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
