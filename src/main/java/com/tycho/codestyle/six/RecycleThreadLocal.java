package com.tycho.codestyle.six;

/**
 * 六-6 【强制】必须回收自定义的 ThreadLocal 变量，尤其在线程池场景下，线程经常会被复用，
如果不清理自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题。
尽量在代理中使用 try-finally 块进行回收。
 */
public class RecycleThreadLocal {
	private ThreadLocal<String> threadLocal= new ThreadLocal<String>();
	private void test() {
		threadLocal.set("test");
		try {
			
		}
		finally {
			threadLocal.remove();
		}
	}
}
