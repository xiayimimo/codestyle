package com.tycho.codestyle.six;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 六-10【强制】在使用尝试机制来获取锁的方式中，进入业务代码块之前，必须先判断当前线程是
否持有锁。锁的释放规则与锁的阻塞等待方式相同。
 *
 */
public class CheckLock {
	public void test(int num) {
		ReentrantLock lock = new ReentrantLock();
		boolean isLocked = lock.tryLock();
		if (isLocked) {
			try {
				int i = 1/num;
			} finally {
				lock.unlock();
			}
		}
	}
}
