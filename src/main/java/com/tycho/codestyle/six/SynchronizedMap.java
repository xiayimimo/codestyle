package com.tycho.codestyle.six;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 六-18 【参考】HashMap 在容量不够进行 resize 时由于高并发可能出现死链，导致 CPU 飙升，在
开发过程中可以使用其它数据结构或加锁来规避此风险。
 */
public class SynchronizedMap<K,V> implements Map<K,V>, Serializable {
	/**
	 * hashmap在resize扩容的时候，需要对原数组的所有键值对重新进行计算和写入新的数组，
	 * 之后指向新生成的数组。当多个线程同时检测到总数量超过门限值的时候就会同时调用resize操作，
	 * 各自生成新的数组并rehash后赋给该map底层的数组table，结果最终只有最后一个线程生成的新
	 * 数组被赋给table变量，其他线程的均会丢失。而且当某些线程已经完成赋值而其他线程刚开始的时候，
	 * 就会用已经被赋值的table作为原始数组，这样也会有问题。
	 * https://www.jianshu.com/p/11c99bf29ad2
	 */
	private static final long serialVersionUID = 1978198479659022715L;
	private final Map<K,V> m;     // Backing Map

	final Object mutex;// Object on which to synchronize

	 

	SynchronizedMap(Map<K,V> m) {

		if (m==null)

			throw new NullPointerException();

		this.m = m;

		mutex = this;

	}

	 

	SynchronizedMap(Map<K,V> m, Object mutex) {

		this.m = m;

		this.mutex = mutex;

	}

	 

	public int size() {

		synchronized(mutex) {return m.size();}

	}

	 

	//***********************************

	//节省空间,删除了大量类似代码

	//***********************************

	public String toString() {

		synchronized(mutex) {return m.toString();}

	}



	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

		

	
}
