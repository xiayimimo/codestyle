package com.tycho.codestyle.seven;
/**
 * 七-9 【推荐】避免采用取反逻辑运算符。
 *
 */
public class ConditionTip {
	public void wrongWay(int num) {
		if(!(num>50)) {
			System.out.println("<= 50");
		}
		else {
			System.out.println("> 50");
		}
	}
	public void rightWay(int num) {
		if(num<=50) {
			System.out.println("<= 50");
		}
		else {
			System.out.println("> 50");
		}
	}
}
