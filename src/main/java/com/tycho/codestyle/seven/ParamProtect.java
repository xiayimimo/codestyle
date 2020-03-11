package com.tycho.codestyle.seven;

import java.util.Date;
/**
 * 七-10【推荐】接口入参保护，这种场景常见的是用作批量操作的接口。
 *
 */
public class ParamProtect {
	public void test(Date date) {
		Date now = new Date();
		Boolean notOneDay = now.getTime()-date.getTime() > 24*60*60*1000 ; 
		if(notOneDay) {
			return;
		}
		else {
			
		}
	}
}
