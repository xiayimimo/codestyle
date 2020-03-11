package com.tycho.codestyle.one;

/**
 * 常量定义
 * 
 * @author ll
 *
 */
public class TwoConstantDefinition {

	/*
	 * 1. 【强制】不允许任何魔法值（即未经预先定义的常量）直接出现在代码中。 反例：String key = "Id#taobao_" +
	 * tradeId; cache.put(key, value); 缓存 get 时，由于在代码复制时，漏掉下划线，导致缓存击穿而出现问题
	 */
	/*
	 * 2. 【强制】在 long 或者 Long 赋值时，数值后使用大写的 L，不能是小写的 l，小写容易跟数 字 1 混淆，造成误解。 说明：Long
	 * a = 2l; 写的是数字的 21，还是 Long 型的 2。
	 */
	/*
	 * 3. 【推荐】不要使用一个常量类维护所有常量，要按常量功能进行归类，分开维护。
	 * 说明：大而全的常量类，杂乱无章，使用查找功能才能定位到修改的常量，不利于理解和维护。 正例：缓存相关常量放在类 CacheConsts
	 * 下；系统配置相关常量放在类 ConfigConsts 下。
	 */
	/*
	 * 4. 【推荐】常量的复用层次有五层：跨应用共享常量、应用内共享常量、子工程内共享常量、 包内共享常量、类内共享常量。 1）
	 * 跨应用共享常量：放置在二方库中，通常是 client.jar 中的 constant 目录下。 2）
	 * 应用内共享常量：放置在一方库中，通常是子模块中的 constant 目录下。
	 * 反例：易懂变量也要统一定义成应用内共享常量，两位工程师在两个类中分别定义了“YES”的变量： 类 A 中：public static final
	 * String YES = "yes"; 类 B 中：public static final String YES = "y";
	 * A.YES.equals(B.YES)，预期是 true，但实际返回为 false，导致线上问题。 3） 子工程内部共享常量：即在当前子工程的
	 * constant 目录下。 4） 包内共享常量：即在当前包下单独的 constant 目录下。 5） 类内共享常量：直接在类内部 private
	 * static final 定义 --------------- 一方库：本工程范围内，各个模块和包之间的相互依赖。
	 * 二方库：引入的同一个公司内部的其他工程。 三方库：公司以外的其他依赖，比如apache，google等
	 */

}
/*
 * 5. 【推荐】如果变量值仅在一个固定范围内变化用 enum 类型来定义。 说明：如果存在名称之外的延伸属性应使用 enum
 * 类型，下面正例中的数字就是延伸信息，表示一年中的 第几个季节。 正例： public enum SeasonEnum { SPRING(1),
 * SUMMER(2), AUTUMN(3), WINTER(4); private int seq; SeasonEnum(int seq) {
 * this.seq = seq; } public int getSeq() { return seq; } }
 */
