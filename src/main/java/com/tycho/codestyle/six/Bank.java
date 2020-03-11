package com.tycho.codestyle.six;
/**
 * 六-8 【强制】对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会
造成死锁。
说明：线程一需要对表 A、B、C 依次全部加锁后才可以进行更新操作，那么线程二的加锁顺序也必须是
A、B、C，否则可能出现死锁。
 *
 */
public class Bank {
	final static Object obj_lock = new Object();

	// Deadlock crisis
	public void transferMoney(Account from, Account to, int number) {
		synchronized (from) {
			synchronized (to) {
				from.debit();
				to.credit();
			}
		}
	}

	// Thread safe
	public void transferMoney2(final Account from, final Account to, int number) {
		class Help {
			void transferMoney2() {
				from.debit();
				to.credit();
			}
		}

		int fromHash = from.hashCode();
		int toHash = to.hashCode();
		if (fromHash < toHash) {
			synchronized (from) {
				synchronized (to) {
					new Help().transferMoney2();
				}
			}
		} else if (toHash < fromHash) {
			synchronized (to) {
				synchronized (from) {
					new Help().transferMoney2();
				}
			}
		} else {
			synchronized (obj_lock) {
				synchronized (to) {
					synchronized (from) {
						new Help().transferMoney2();
					}
				}
			}
		}
	}
}