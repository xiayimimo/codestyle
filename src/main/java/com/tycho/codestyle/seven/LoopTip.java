package com.tycho.codestyle.seven;
/**
 * 七-8 【推荐】循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变
量、获取数据库连接，进行不必要的 try - catch 操作 （ 这个 try - catch 是否可以移至循环体
外 ） 。
 *
 */
public class LoopTip {
	public void wrongWay() {
		String text = "abcdefghijklmn";
		for(int i=0;i<text.length();i++) {
			char character = text.charAt(i);
			String intro = "This is ";
			System.out.println(intro+character);
		}
	}
	public void rightWay() {
		String text = "abcdefghijklmn";
		String intro = "This is ";
		for(int i=0;i<text.length();i++) {
			System.out.println(intro+text.charAt(i));
		}
	}
}
