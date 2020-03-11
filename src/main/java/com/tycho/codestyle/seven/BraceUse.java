package com.tycho.codestyle.seven;
/**
 * 七-3 【强制】在 if / else / for / while / do 语句中必须使用大括号。
 *
 */
public class BraceUse {
	public void test() {
		int i = 0;
		while(i<5) {
			if(i%2==0) {
				i++;
			}
			else {
				i = i + 2;
			}
		}
		
		do {
			i++;
		}while(i<5);
		
		int sum = 0;
		for(int j=0;j<5;j++) {
			sum+=j;
		}
	}
}
