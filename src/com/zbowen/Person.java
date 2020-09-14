package com.zbowen;

public class Person {

	
	private int age;
	private String name;
	
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}


	/**
	 * 该方法在对象被垃圾回收器回收前调用
	 */
	protected void finalize() throws Throwable {
		System.out.println("Person -- finalize:"+this.toString());
		super.finalize();
	}
}
