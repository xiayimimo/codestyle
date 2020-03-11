package com.tycho.codestyle.six;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
/**
 * 六-17 【参考】 volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但
是如果多写，同样无法解决线程安全问题。
 *
 */
public class Increment {
	public void test() {
		AtomicInteger count = new AtomicInteger();
		count.addAndGet(1);
		
		LongAdder adder = new LongAdder();
		adder.increment();
	}
	/*
	 *内存可见性（Memory Visibility）是指当某个线程正在使用对象状态而另一个线程在同时修改该状态，需要确保当一个线程修改了对象状态后，其他线程能够立即看到发生的状态变化。

由于线程之间的交互都发生在主内存中，但对于变量的修改又发生在自己的工作内存中，经常会造成读写共享变量的错误，我们也叫可见性错误。

可见性错误是指当读操作与写操作在不同的线程中执行时，我们无法确保执行读操作的线程能适时地看到其他线程写入的值，有时甚至是根本不可能的事情。

关键字volatile的作用是告知JIT编译器不要对被标记变量执行任何可能影响其访问顺序的优化。该关键字警告JIT编译器，该变量可能会被某个线程更改，
所以任何对该变量的读写访问都需要忽略本地cache并直接对内存进行操作。之前我将这个改动称为快速修复，是因为如果我们将所有变量都标记为volatile
的话，虽然可以完全规避此类问题，但却会使每次变量访问都要跨越内存栅栏并最终导致程序性能下降。此外，在多个字段被多个线程并发访问的场景下，
由于针对每个volatile字段的访问都是各自独立处理的，并且也无法将这些访问统一协调成一次访问，所以volatile关键字无法保证整体操作的原子性。
该问题所造成的后果是，线程很可能对某些字段只能看到其中间结果，而对另一些变量则看到的是最终的变更结果。


	 * 
	 */
}
