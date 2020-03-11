package com.tycho.codestyle.nine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 九-1【强制】在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。
 * @author shenjuntao
 * @date 2020年1月3日
 */
public class RegTip {
	private static final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	 
	public void func(String input) {
	    Matcher m = pattern.matcher(input);
	    if (m.matches()) {
	    	System.out.println("is a number");
	    }
	}

}
