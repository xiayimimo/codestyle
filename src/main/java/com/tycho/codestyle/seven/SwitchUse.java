package com.tycho.codestyle.seven;

/**
 * 七-1【强制】在一个 switch 块内，每个 case 要么通过 continue/break/return 等来终止，要么
注释说明程序将继续执行到哪一个 case 为止；在一个 switch 块内，都必须包含一个
default 语句并且放在最后，即使它什么代码也没有。
 *
 */
public class SwitchUse {
	public void test(int num) {
		switch(num) {
			case 1:
				System.out.println("num是1");
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.println("num大于1");//执行到这里为止
				break;
			default:
				System.out.println("num为其他值");
				break;
		}
	}
}
