package com.tycho.codestyle.eight;
/**
 * 八-1【强制】类、类属性、类方法的注释必须使用 Javadoc 规范，使用内容格式，不得使用
 * xxx 方式。
 * 八-2 【强制】所有的抽象方法 （ 包括接口中的方法 ） 必须要用 Javadoc 注释、除了返回值、参数、
 * 异常说明外，还必须指出该方法做什么事情，实现什么功能。
 * 八-3 【强制】所有的类都必须添加创建者和创建日期。
 * @author shenjuntao
 * @date 2020年1月3日
 */
public abstract class NoteTip {
	
	/**
	 * 设置名称
	 * @param name 名称
	 * @return 设置成功与否
	 * @throws NullPointerException name为空时抛出异常
	 */
	public abstract Boolean setName(String name) throws NullPointerException;
}
