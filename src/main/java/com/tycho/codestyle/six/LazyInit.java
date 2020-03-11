package com.tycho.codestyle.six;
/**
 * 六-16 【推荐】在并发场景下，通过双重检查锁 （double - checked locking） 实现延迟初始化的优化
问题隐患 ( 可参考 The " Double - Checked Locking is Broken " Declaration) ，推荐解决方案中较为
简单一种 （ 适用于 JDK 5 及以上版本 ） ，将目标属性声明为 volatile 型 。
 *
 */
public class LazyInit {
	private static volatile Singleton instance = null; // 添加volatile修饰符

	private LazyInit() {
	}

	public static Singleton getInstance() {
		if (instance == null) {//1
			synchronized (Singleton.class) {//2
				if (instance == null) //3
					instance = new Singleton();//4
				}
			}
		return instance;
	}
	/**
	 * 这样的问题在于初始化代码：

　　　　 instance = new Singleton();
	JVM会将这段代码分成三步去执行：

　　a.分配内存空间；

　　b.构造Singleton；

　　c.将instance指向构造的实例。

　　如果执行的过程是a->b->c的话，那上面的代码是没有问题的，但是有时JVM会基于指令优化的目的将指令重排，导致指令执行流程变为a->c->b。
这样当线程A执行到4开始初始化单例对象的c流程时，线程B执行到1处，由于instance对象已经将内部指针指向分配的内存空间（即不为null）,
会直接返回未完全构造好的实例，从而出错

由于volatile自带的“禁止指令重优化”语义，初始化语句只能按照a->b->c的顺序进行执行
	 */
}
