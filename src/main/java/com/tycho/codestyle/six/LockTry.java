package com.tycho.codestyle.six;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 六-9 【强制】在使用阻塞等待获取锁的方式中，必须在 try 代码块之外，并且在加锁方法与 try 代
码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在 finally 中无法解锁。
 *
 */
public class LockTry {
	public void wrongWay(int num) {
		ReentrantLock lock = new ReentrantLock();
		try {
			// 如果此处抛出异常，则直接执行 finally 代码块
			int i = 5/num;
			// 无论加锁是否成功，finally 代码块都会执行
			lock.lock();
			i++;
		} finally {
			lock.unlock();
		}
	}
	
	public void rightWay(int num) {
		ReentrantLock lock = new ReentrantLock();
		// ...
		lock.lock();
		try {
			int i = 5/num;
			i++;
		} finally {
			lock.unlock();
		}
	}
}
