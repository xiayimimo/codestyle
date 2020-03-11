package com.tycho.codestyle.seven;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
/**
 * 七-6 【推荐】除常用方法（如 getXxx/isXxx ）等外，不要在条件判断中执行其它复杂的语句，将复
杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。
 *
 */
public class IfCondition {
	public void test(String num) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		Boolean isNumeric = StringUtils.hasText(num) && pattern.matcher(num).matches();  
		if(isNumeric) {
			System.out.println("is a number");
		}
		else {
			System.out.println("not a number");
		}
		
	}
}
