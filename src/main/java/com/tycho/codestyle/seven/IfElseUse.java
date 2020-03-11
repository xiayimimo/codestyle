package com.tycho.codestyle.seven;

import org.springframework.util.StringUtils;
/**
 * 七-5 【推荐】表达异常的分支时，少用 if-else 方式 ，这种方式可以改写成：
if (condition) {
...
return obj;
}
// 接着写 else 的业务逻辑代码;
 *
 */
public class IfElseUse {
	public static void test(String input) {
		if(StringUtils.isEmpty(input)) {
			return;
		}
		String[] arr = input.split(",");
		if(arr.length!=2){
			return;
		}
		String name1 = arr[0];
		String name2 = arr[1];
		name1 = StringUtils.capitalize(name1);
		name2 = StringUtils.capitalize(name2);
		System.out.println("hello "+arr[0]+",hello "+arr[1]);
	}
	
	public static void main(String[] args) {
		test("lucy,lily");
	}
}
