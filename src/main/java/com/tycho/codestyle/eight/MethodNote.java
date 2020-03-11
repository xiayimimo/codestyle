package com.tycho.codestyle.eight;

import org.springframework.util.StringUtils;
/**
 * 八-4【强制】方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释
 * 使用注释，注意与代码对齐。
 * @author shenjuntao
 * @date 2020年1月3日
 */
public class MethodNote {
	public void test(String input) {
		//把input字符串首字母大写
		input = StringUtils.capitalize(input);
		/*
		 * 截取字符串，保证最多10个字符
		 */
		if(input.length()>10) {
			input = input.substring(0,10);
		}
	}
}
