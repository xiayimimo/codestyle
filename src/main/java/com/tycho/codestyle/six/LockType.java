package com.tycho.codestyle.six;

/**
 * 六-11【强制】并发修改同一记录时，避免更新丢失，需要加锁。要么在应用层加锁，要么在缓存
加锁，要么在数据库层使用乐观锁，使用 version 作为更新依据。
 *
 */
public class LockType {
	
	/*
	 * 乐观锁
	 * 假定每次修改都没有线程竞争，在修改时判断数据有没有被修改过，没有才修改成功，可以通过版本号、修改时间等来判断
	 * start transaction
	 * select update_time from product where id = 1
	 * update product set name='abc' where id = 1 and update_time = 上面查出的值
	 * commit
	 */
	
	/*
	 * 悲观锁
	 * 假定每次修改都有线程竞争，总是先锁定数据，再修改，修改完再解锁
	 * start transaction
	 * select * from account where name='张三' for update
	 * update account set balance=100 where name='张三'
	 * commit
	 */
}
