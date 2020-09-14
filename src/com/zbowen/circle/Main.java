package com.zbowen.circle;

public class Main {

	public static void main(String[] args) {
		CircleArrayList<Integer> list = new CircleArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			list.add(list.size(), i+1);
		}
		System.out.println(list);
		list.add(0, 99);
		System.out.println(list);
		list.add(0, 100);
		System.out.println(list);
		list.set(2, 98);
		System.out.println(list);
		list.add(6, 33);
		System.out.println(list);
		
	}
}
