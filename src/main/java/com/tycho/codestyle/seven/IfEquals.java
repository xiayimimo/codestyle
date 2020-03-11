package com.tycho.codestyle.seven;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 七-7【推荐】不要在其它表达式（尤其是条件表达式）中，插入赋值语句。
 *
 */
public class IfEquals {
	public Lock getLockWrong(boolean fair) {
		boolean sync = false;
		int count = 0;
		// 算术表达式中出现赋值操作，容易忽略 count 值已经被改变
		int threshold = (count = Integer.MAX_VALUE) - 1;
		// 条件表达式中出现赋值操作，容易误认为是 sync==fair
		return (sync = fair) ? new ReentrantLock(true) : new ReentrantLock(false);
	}
	
	public Lock getLockRight(boolean fair) {
		int count = Integer.MAX_VALUE;
		int threshold = count - 1;
		boolean sync = fair;
		return fair ? new ReentrantLock(true) : new ReentrantLock(false);
	}
}
