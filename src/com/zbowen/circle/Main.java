package com.zbowen.circle;

public class Main {

	public static void main(String[] args) {
		CircleArrayList<Integer> list = new CircleArrayList<Integer>();
		System.out.println(list.size());
		for (int i = 0; i < 100; i++) {
			list.add(list.size(), i+1);
		}
		for (int i = 0; i < 88; i++) {
			System.out.println(list.remove(2));
		}
		System.out.println(list);
	}
}
