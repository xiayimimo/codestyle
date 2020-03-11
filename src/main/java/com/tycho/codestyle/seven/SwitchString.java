package com.tycho.codestyle.seven;

import org.springframework.util.StringUtils;
/**
 * 七-2 【强制】当 switch 括号内的变量类型为 String 并且此变量为外部参数时，必须先进行 null
判断。
 *
 */
public class SwitchString {
	public static void method(String param) {
		if(StringUtils.hasText(param)) {
			switch (param) {
			case "sth":
				System.out.println("it's sth");
				break;
			case "null":
				System.out.println("it's null");
				break;
			default:
				System.out.println("default");
			}
		}
		else {
			System.out.println("it's null or empty");
		}
	}
}
