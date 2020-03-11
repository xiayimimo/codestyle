package com.tycho.codestyle.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

/**
 * 集合处理
 * @author ll
 *
 */
public class FiveCollectionDeal {

	public static void main(String[] args) {
		/*
		 * 1. 【强制】关于 hashCode 和 equals 的处理，遵循如下规则： 1） 只要覆写 equals，就必须覆写
		 * hashCode。 2） 因为 Set 存储的是不重复的对象，依据 hashCode 和 equals 进行判断，所以 Set
		 * 存储的对象必须覆 写这两个方法。 3） 如果自定义对象作为 Map 的键，那么必须覆写 hashCode 和 equals。
		 * 说明：String 已覆写 hashCode 和 equals 方法，所以我们可以愉快地使用 String 对象作为 key 来使用
		 */
		Person person1 = new Person("ll",1);
		Person person2 = new Person("ll",1);
		System.out.println("stu:" + person1.equals(person2));
		Set<Person> set = new HashSet<>();
		set.add(person1);
		System.out.println("s1 hashCode:" + person1.hashCode());
		System.out.println("add s1 size:" + set.size());
		set.add(person2);
		System.out.println("s2 hashCode:" + person2.hashCode());
		System.out.println("add s2 size::" + set.size());
		Map<Person,Object> map = new HashMap<>();
		System.out.println("-----------------");
		map.put(person1, 1);
		map.put(person2, 2);
		System.out.println(map.get(person1)+":"+map.get(person2));
		/*
		 * 2. 【强制】ArrayList 的 subList 结果不可强转成 ArrayList，否则会抛出 ClassCastException
		 * 异 常，即 java.util.RandomAccessSubList cannot be cast to
		 * java.util.ArrayList。 说明：subList 返回的是 ArrayList 的内部类 SubList，并不是
		 * ArrayList 而是 ArrayList 的一个视图，对 于 SubList 子列表的所有操作最终会反映到原列表上。
		 */
		ArrayList<Person> list = new ArrayList<>();
		list.add(person1);
		list.add(person2);
		//ArrayList<Person> list1 =  (ArrayList<Person>) list.subList(0, 1);
		List<Person> list1 = list.subList(0, 1);
		list1.add(person1);
		System.out.println(list.size());// 3 子集合操作影响父集合
		/*
		 * 3. 【强制】使用 Map 的方法 keySet()/values()/entrySet()返回集合对象时，不可以对其进行添
		 * 加元素操作，否则会抛出 UnsupportedOperationException 异常。
		 */
		Set<Person> set1 = map.keySet();
		//set1.add(person2); //报错
		Set<Entry<Person, Object>> set2 = map.entrySet();
		/*set2.add(new Entry(){
			@Override
			public Object getKey() {
				return person1;
			}

			@Override
			public Object getValue() {
				return 3;
			}

			@Override
			public Object setValue(Object value) {
				return 3;
			}
			
		});// 报错 */
		Collection<Object> map1 = map.values();
		//map1.add(person1);// 报错 UnsupportedOperationException
		/*
		 * 4. 【强制】Collections 类返回的对象，如：emptyList()/singletonList()等都是 immutable
		 * list，不可对其进行添加或者删除元素的操作。 反例：如果查询无结果，返回
		 * Collections.emptyList()空集合对象，调用方一旦进行了添加元素的操作，就 会触发
		 * UnsupportedOperationException 异常。
		 */
		List<Object> list2 = Collections.emptyList();// 产生的是 immutable（不可变） list
		//list2.add(person1);// 报错 UnsupportedOperationException
		/*
		 * 5. 【强制】在 subList 场景中，高度注意对原集合元素的增加或删除，均会导致子列表的遍 历、增加、删除产生
		 * ConcurrentModificationException 异常。
		 */
		list.remove(0);// 原集合
		//list1.add(person2);// 子集合操作报错ConcurrentModificationException
		/* 6 
		使用 toArray 带参方法，数组空间大小的 length：
		1） 等于 0，动态创建与 size 相同的数组，性能最好。
		2） 大于 0 但小于 size，重新创建大小等于 size 的数组，增加 GC 负担。
		3） 等于 size，在高并发情况下，数组创建完成之后，size 正在变大的情况下，负面影响与上相同。
		4） 大于 size，空间浪费，且在 size 处插入 null 值，存在 NPE 隐患。*/
		//Person[] arrPerson = (Person[]) list.toArray();// 报错
		Person[] arrPerson = list.toArray(new Person[0]);// 推荐
		/*
		 * 7. 【强制】在使用 Collection 接口任何实现类的 addAll()方法时，都要对输入的集合参数进行 NPE 判断。 说明：在
		 * ArrayList#addAll 方法的第一行代码即 Object[] a = c.toArray(); 其中 c 为输入集合参数，如果
		 * 为 null，则直接抛出异常
		 */
		//Collections.addAll(null, null);// 异常
		/*
		 * 8. 【强制】使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方 法，它的
		 * add/remove/clear 方法会抛出 UnsupportedOperationException 异常。 说明：asList
		 * 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。Arrays.asList 体现的是适
		 * 配器模式，只是转换接口，后台的数据仍是数组。 String[] str = new String[] { "yang", "hao" };
		 * List list = Arrays.asList(str); 第一种情况：list.add("yangguanbao"); 运行时异常。
		 * 第二种情况：str[0] = "changed"; 也会随之修改，反之亦然。
		 */
		List<Object> listArr = Arrays.asList(arrPerson);
		//listArr.add(person1);// 报错
		/*
		 * 9. 【强制】泛型通配符<? extends T>来接收返回的数据，此写法的泛型集合不能使用 add 方 法，而<? super
		 * T>不能使用 get 方法，作为接口调用赋值时易出错。 说明：扩展说一下 PECS(Producer Extends Consumer
		 * Super)原则：第一、频繁往外读取内容的，适合 用<? extends T>。第二、经常往里插入的，适合用<? super T>
		 * 参考：https://www.cnblogs.com/chenxibobo/p/9655236.html 
		 * https://blog.csdn.net/qq_29951485/article/details/88068338
		 */		
		Man man1 = new Man("ll",2);
		List<? extends Man> list3 = new ArrayList<>();
		//list3.add(man1);// 报错
		//Man man3 = list3.get(0);
		List<? super Man> list4 = new ArrayList<>();
		//list4.add(person1); // 报错
		list4.add(man1);
		list4.add(new Woman("hh",22));
		//Man man2 = list4.get(0);
		/*
		 * 10.【强制】在无泛型限制定义的集合赋值给泛型限制的集合时，在使用集合元素时，需要进行
		instanceof 判断，避免抛出 ClassCastException 异常。
		说明：毕竟泛型是在 JDK5 后才出现，考虑到向前兼容，编译器是允许非泛型集合与泛型集合互相赋值。
		反例：
		List<String> generics = null;
		List notGenerics = new ArrayList(10);
		notGenerics.add(new Object());
		notGenerics.add(new Integer(1));
		generics = notGenerics;
		// 此处抛出 ClassCastException 异常
		String string = generics.get(0);*/
		/*
		 * 11.【强制】不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使用
		Iterator 方式，如果并发操作，需要对 Iterator 对象加锁。
		正例：
		List<String> list = new ArrayList<>(); 
		list.add("1"); 
		list.add("2"); 
		Iterator<String> iterator = list.iterator(); 
		while (iterator.hasNext()) { 
		String item = iterator.next(); 
		if (删除元素的条件) { 
		iterator.remove(); 
		} 
		}
		反例：
		for (String item : list) { 
		if ("1".equals(item)) { 
		list.remove(item); 
		} 
		}*/
		/*
		 * 12.【强制】在 JDK7 版本及以上，Comparator 实现类要满足如下三个条件，不然 Arrays.sort，
		Collections.sort 会抛 IllegalArgumentException 异常。
		说明：三个条件如下 1） x，y 的比较结果和 y，x 的比较结果相反。
		2） x>y，y>z，则 x>z。 3） x=y，则 x，z 比较结果和 y，z 比较结果相同。
		反例：下例中没有处理相等的情况，交换两个对象判断结果并不互反，不符合第一个条件，在实际使用中
		可能会出现异常。*/
		Collections.sort(list, new Comparator<Person>() { 
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge() > o2.getAge() ? 1 : -1;
			} 
			});
		Arrays.sort(arrPerson, 
		new Comparator<Person>() { 
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge() > o2.getAge() ? 1 : -1;
			} 
			});
		/*
		 * 13.【推荐】集合泛型定义时，在 JDK7 及以上，使用 diamond 语法或全省略。 说明：菱形泛型，即
		 * diamond，直接使用<>来指代前边已经指定的类型。 
		 * 正例：
		 */
		// diamond 方式，即<>
		HashMap<String, String> userCache = new HashMap<>(16);
		// 全省略方式
		ArrayList<User> users = new ArrayList(10);
		/*
		 * 14.【推荐】集合初始化时，指定集合初始值大小。
		说明：HashMap 使用 HashMap(int initialCapacity) 初始化。
		正例：initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即 loader factor）默认
		为 0.75，如果暂时无法确定初始值大小，请设置为 16（即默认值）。
		反例：HashMap 需要放置 1024 个元素，由于没有设置容量初始大小，随着元素不断增加，容量 7 次被
		迫扩大，resize 需要重建 hash 表，严重影响性能。
		集合初始化  20个元素 初始大小 20/0.75 + 1 = 27
		*/
		Map<String,Object> map2 = new HashMap<>(27);
		/*
		 * 15.【推荐】使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历。 说明：keySet 其实是遍历了
		 * 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出 key 所对应 的 value。而 entrySet
		 * 只是遍历了一次就把 key 和 value 都放到了 entry 中，效率更高。如果是 JDK8， 使用 Map.forEach 方法。
		 * 正例：values()返回的是 V 值集合，是一个 list 集合对象；keySet()返回的是 K 值集合，是一个 Set 集合
		 * 对象；entrySet()返回的是 K-V 值组合集合。
		 */
		Set<Entry<String, Object>> set3 = map2.entrySet();
		//推荐
		for (Entry<String, Object> entry : set3) {
			entry.getKey();entry.getValue();
		}
		//不推荐
		Set<String> set4 = map2.keySet();
		for (Iterator iterator = set4.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			map2.get(string);  
		}
		// jdk1.8推荐
		map2.forEach((k,v) -> System.out.println(k+":"+v));
		/*【推荐】高度注意 Map 类集合 K/V 能不能存储 null 值的情况，如下表格：
		集合类                                            Key                 Value               Super              说明
		Hashtable          不允许为 null          不允许为 null           Dictionary         线程安全
		ConcurrentHashMap  不允许为 null          不允许为 null           AbstractMap       锁分段技术（JDK8:CAS）
		TreeMap            不允许为 null             允许为 null         AbstractMap        线程不安全
		HashMap            允许为 null              允许为 null           AbstractMap         线程不安全
		*/
		/*
		 * 17 有序性是指遍历的结果是按某种比较规则依次排列的。稳定性指集合每次遍历的元素次序是一定 ArrayList 是
		 * order/unsort；HashMap 是 unorder/unsort；TreeSet 是 order/sort
		 */
		/*
		 * 18 利用集合特性处理数据 比如利用 Set 元素唯一的特性，可以快速对一个集合进行去重操作，避免使用 List 的 contains
		 * 方法进行遍历、对比、去重操作。
		 */
		List<String> listOld = new ArrayList<>();
		listOld.add("b");listOld.add("a");listOld.add("b");listOld.add("c");listOld.add("a");
		System.out.println("去重前 " + listOld + "");
	    List<String> listNew = new ArrayList<String>();
	    Set<String> set5 = new HashSet<>();
	    set5.addAll(listOld);
	    listNew.addAll(set5);//去重后集合无序
	   /*for (String i : listOld) {
	        if (set5.add(i)) {
	            listNew.add(i);
	        }
	    }*/
	    System.out.println("set去重后 " + listNew + "");
		//jdk1.8流特性去重
		List<String> uniqueList = listOld.stream().distinct().collect(Collectors.toList());
		System.out.println("stream去重后 " + uniqueList + "");
	}
	
}

class Person {
	private String name;
	public int age;
	public Person(){
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public int getAge() {
		return age;
	}
 
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}

class Man extends Person{
	public Man(String name, int age) {
		super(name, age);
	}
	
	public static void a(){
		
	}
}
class Woman extends Man{
	public Woman(String name, int age) {
		super(name, age);
	}
	
}