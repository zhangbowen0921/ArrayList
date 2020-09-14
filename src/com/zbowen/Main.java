package com.zbowen;

public class Main {

	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<>();
		Person person = new Person(15, "xiaohong");
		list.add(person);
		list.add(new Person(12, "jack"));
		list.add(new Person(18, "Tom"));
		list.add(new Person(16, "lucy"));
		list.set(2, new Person(20, "小米"));
		list.add(3, new Person(24, "华为"));
		System.out.println(list.remove(0));
		System.out.println(list.contains(person));
		System.out.println(list);
		list.clear();
		System.gc();
		
	}

}
