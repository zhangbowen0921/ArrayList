package com.zbowen;

import com.zbowen.circle.CircleArrayList;

/**
 * 自己实现 动态数组
 * 
 * @author zbowen
 *
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> implements IDynamicArray<E> {

	private int size;
	private E[] elements;

	private static final int DEFAULT_CAPATICY = 10;

	public ArrayList(int capaticy) {
		capaticy = capaticy < DEFAULT_CAPATICY ? DEFAULT_CAPATICY : capaticy;
		elements = (E[]) new Object[capaticy];
	}

	public ArrayList() { 
		this(DEFAULT_CAPATICY);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(E element) {
		return indexof(element) != -1 ? true : false;
	}

	public void add(E element) {
		add(size, element);
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E old = elements[index];
		elements[index] = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapaticy();
		for (int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}
		elements[index] = element;
		size++;
	}

	private void ensureCapaticy() {
		if (size >= elements.length) {
			// 扩容
			int newCapaticy = size + (size >> 1);
			System.out.println("数组扩容："+size+"-->"+newCapaticy);
			E[] arr = (E[]) new Object[newCapaticy];
			for (int i = 0; i < elements.length; i++) {
				arr[i] = elements[i];
			}
			elements = arr;
		}

	}

	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOutOfBoundsException(index);
		}
	}

	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOutOfBoundsException(index);
		}
	}

	private void outOutOfBoundsException(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + " Size:" + size);
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		E remove = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i-1] = elements[i];
		}
		elements[--size] = null;
		return remove;
	}

	@Override
	public int indexof(E element) {
		if (element == null) {
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < elements.length; i++) {
				if (element.equals(elements[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				builder.append(", ");
			}
			builder.append(elements[i]);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int removeElement(E element) {
		int index = indexof(element);
		remove(index);
		return index;
	}
	
	public static void main(String[] args) {
		//注意：这里的ArrayList是我们自己实现的ArrayList 不是java.util.ArrayList<E>
		//ArrayList<Integer> list = new ArrayList<Integer>();
		//优化后的ArrayList ---> CircleArrayList
		CircleArrayList<Integer> list = new CircleArrayList<Integer>();
		//首先往集合里面存储10个元素看一下底层的存储过程
		System.out.println(list);
		for (int i = 0; i < 10; i++) {
			list.add(0, i+1);
			System.out.println(list);
		}
		//然后 继续添加一个 这时数组应该需要扩容
		list.add(11);
		System.out.println(list);
		//删除集合index==0号位置的元素 
		list.remove(0);
		System.out.println(list);
		//向index==0的位置添加10个元素
		for (int i = 90; i < 100; i++) {
			list.add(0, i);
			System.out.println(list);
		}
		//向index==0的位置删除12个元素
		for (int i = 0; i < 12; i++) {
			list.remove(0);
			System.out.println(list);
		}
	}
}
