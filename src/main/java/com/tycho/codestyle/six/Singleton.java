package com.tycho.codestyle.six;

public class Singleton {
	/**
	 * 六-1 【强制】获取单例对象需要保证线程安全，其中的方法也要保证线程安全。
	 */
	private static MyObject myObject=new MyObject();
	public synchronized static MyObject getInstance() {
		return myObject;
	}
	
}
