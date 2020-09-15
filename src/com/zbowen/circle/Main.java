package com.zbowen.circle;

public class Main {

	public static void main(String[] args) {
		CircleArrayList<Integer> list = new CircleArrayList<Integer>();
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15); 
		int remove = list.remove(15);
		System.out.println(remove);  //13
		list.add(0, 1);
		list.add(0, 2);
		list.add(0, 3);
		list.set(2, 0);
		list.add(3, 9); //3 2 0 9 10 11 12 14 15
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}
}
