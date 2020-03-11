package com.tycho.codestyle.six;

import java.util.concurrent.CyclicBarrier;

/**
 * 六-19  【参考】ThreadLocal 对象使用 static 修饰，ThreadLocal 无法解决共享对象的更新问题。
 * 
 * ThreadLocal 无法解决共享对象的更新问题。所以使用某个引用来操作共享对象时，依然需要进行线程同步
 *
 */
public class ThreadLocalUtil {
	private static final ThreadLocal<Integer> testThreadLocal = new ThreadLocal<>();

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(4);
		for (int i = 0; i < 4; i++) {
			new Thread(new TestThread(barrier)).start();
		}
	}

	static class TestThread implements Runnable {
		private CyclicBarrier barrier;

		public TestThread(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public synchronized void run() {
			try {
				barrier.await();
				for (int i = 0; i < 100; i++) {
					Integer value = testThreadLocal.get();
					if (value == null) {
						value = 0;
					}
					Integer sum = value + i;
					testThreadLocal.set(sum);
				}
				System.out.println(Thread.currentThread().getName() + " sum is " + testThreadLocal.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
