package com.tycho.codestyle.six;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 六-5【强制】 SimpleDateFormat 是线程不安全的类，一般不要定义为 static 变量，如果定义为
static ，必须加锁，或者使用 DateUtils 工具类。
    解释：如果多个线程使用同一个SimpleDateFormat 的对象进行日期和字符串的转化，则会有异常抛出。
    通过查看源码发现，原来SimpleDateFormat类内部有一个Calendar对象引用,它用来储存和这个SimpleDateFormat相关的日期信息,
    例如sdf.parse(dateStr),sdf.format(date) 诸如此类的方法参数传入的日期相关String,Date等等, 都是交由Calendar引用来
    储存的.这样就会导致一个问题,如果你的SimpleDateFormat是个static的, 那么多个thread 之间就会共享这个SimpleDateFormat, 
    同时也是共享这个Calendar引用。单例、多线程、又有成员变量（这个变量在方法中是可以修改的），这个场景是不是很像servlet，在高并发的情况下，
    容易出现幻读成员变量的现象，故说SimpleDateFormat是线程不安全的对象。

 *
 */
public class DateFormatter {
	private void test() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		LocalDate date = LocalDate.parse("2017 06 17", formatter);
		System.out.println(formatter.format(date));
	}
}
