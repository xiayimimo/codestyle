package com.tycho.codestyle.seven;

/**
 * 七-4【强制】在高并发场景中，避免使用”等于”判断作为中断或退出的条件。
 *
 */
public class EndCondition {
	private static ThreadLocal<Integer> index = new ThreadLocal<Integer>();
	public static class T extends Thread {	
        @Override	
        public void run() {	
        	if(index.get() >=0) {
        		int count = index.get();
        		count--;
        		index.set(count);
        	}
        	else {
        		this.interrupt();
        	}
        }	
    }	
    public static void main(String[] args) throws InterruptedException {
    	index.set(5);
    	for(int i=0;i<10;i++) {
    		T t = new T();	
    		t.start();	
    	}
    }
	
}
