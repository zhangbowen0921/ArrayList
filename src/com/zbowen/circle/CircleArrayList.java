package com.zbowen.circle;

import com.zbowen.IDynamicArray;

/**
 * 循环动态数组 ArrayList优化
 * @author zbowen
 *
 * @param <E>
 */
@SuppressWarnings("all")
public class CircleArrayList<E> implements IDynamicArray<E> {

	private int size;
	private E[] elements;
	private int first; //首元素的索引
	
	private static final int DEFAULT_CAPATICY = 10;
	
	private static final int NOT_FOUND_ELEMENT = -1;
	
	public CircleArrayList() {
		this(DEFAULT_CAPATICY);
	}
	
	public CircleArrayList(int capaticy) {
		capaticy = capaticy>DEFAULT_CAPATICY?capaticy:DEFAULT_CAPATICY;
		elements = (E[]) new Object[capaticy];
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public boolean contains(E element) {
		return indexof(element) != NOT_FOUND_ELEMENT;
	}

	@Override
	public void add(E element) {
		add(size, element);
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		index = first + index;
		if (index >=elements.length) {
			index -= elements.length;
		}
		return elements[index];
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		index = first + index;
		if (index >=elements.length) {
			index -= elements.length;
		}
		E old = elements[index];
		elements[index] = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapaticy();
		if (index==0) {
			first -= 1;
			if(first<0) first += elements.length;
			if (size==0) first = 0;
			elements[first] = element;
		}else if(index==size) {
			index = first + size - elements.length;
			if (index<0) {
				index = first + size;
			}
			elements[index] = element;
		}else {
			//向中间添加节点
			int temp = size - index;
			if (temp < index) { //说明 index>size/2 将index及以后的元素向后移动一个位置
				//计算出 尾元素的 索引
				int end = first + size - elements.length - 1;
				if (end<0) {
					end = first + size - 1;
				}
				//将index及以后的元素往后挪动一个位置
				for (int i = size; i > index; i--) {
					//System.out.println("往后移动");
					int next = end + 1;
					if (next == elements.length) {
						next = 0;
					}
					elements[next] = elements[end];
					end = end-1;
					if (end<0) {
						end = elements.length - 1;
					}
				}
				end += 1;
				if (end>=elements.length) {
					end = 0;
				}
				elements[end] = element;
			}else {
				//将index及以后的元素往后挪动一个位置
				int oldFirst = first;
				first = first - 1;
				if(first<0) {
					first = elements.length - 1;
				}
				for (int i = 0; i < index; i++) {
					//System.out.println("往前移动");
					int next = oldFirst - 1;
					if (next < 0) {
						next = elements.length - 1;
					}
					elements[next] = elements[oldFirst];
					oldFirst = oldFirst + 1;
					if (oldFirst>=elements.length) {
						oldFirst = 0;
					}
				}
				oldFirst -= 1;
				if (oldFirst<0) {
					oldFirst = elements.length - 1;
				}
				elements[oldFirst] = element;
			}
		}
		size++;
	}
	
	private void ensureCapaticy() {
		if (size<elements.length) return;
		int capaticy = size + (size >> 1);
		System.out.println("数组扩："+size+"-->"+capaticy);
		E[] temp = (E[]) new Object[capaticy];
		int index = first;
		for (int i = 0; i < size; i++) {
			if (index>=elements.length) {
				index = 0;
			}
			temp[i] = elements[index];
			index += 1;
		}
		elements = temp;
		first = 0;
	}
	
	private void reduceCapaticy() {
		if (elements.length<=DEFAULT_CAPATICY) return;
		int half = elements.length>>1;
		if (size > half) return;
		//如果数组中存储的元素还不及数组长度的一半 就进行缩容 前提 缩小一半
		//int capaticy = half + (half>>1);
		int capaticy = half;
		System.out.println("数组缩容"+elements.length+"-->"+capaticy);
		E[] temp = (E[]) new Object[capaticy];
		int index = first;
		for (int i = 0; i < size; i++) {
			if (index>=elements.length) {
				index = 0;
			}
			temp[i] = elements[index];
			index += 1;
		}
		elements = temp;
		first = 0;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		E oldElement = null;
		if (index==0) {
			//删除头部元素 
			oldElement = elements[first];
			elements[first] = null;
			first += 1;
			if(first==elements.length) first = 0;
		}else if (index == size-1) {
			//删除尾部元素
			index = first + size - elements.length - 1;
			if (index<0) {
				index = first + size - 1;
			}
			oldElement = elements[index];
			elements[index--] = null;
		}else {
			//删除中间元素
			int temp = size - index - 1;
			if (index > temp) { //index后面的元素往前挪动
				//计算出index索引对应的真实索引
				int realIndex = first + index - elements.length;
				if (realIndex<0) {
					realIndex = first + index;
				}
				oldElement = elements[realIndex];
				for (int i = 0; i < temp; i++) {
					int next = realIndex + 1;
					if (next >= elements.length) {
						next = 0;
					}
					elements[realIndex] = elements[next];
					realIndex = next;
				}
				
				elements[realIndex] = null;
			}else { //index 前面的元素往后挪动
				//计算出index索引对应的真实索引
				int realIndex = first + index - elements.length;
				if (realIndex<0) {
					realIndex = first + index;
				}
				oldElement = elements[realIndex];
				for (int i = 0; i < index; i++) {
					int pre = realIndex-1;
					if (pre<0) {
						pre = elements.length - 1;
					}
					elements[realIndex] = elements[pre];
					realIndex = pre;
				}
				elements[first] = null;
				first = first + 1 >= elements.length?0:first + 1;
			}
		}
		size--;
		reduceCapaticy();
		return oldElement;
	}

	@Override
	public int indexof(E element) {
		if (element!=null) {
			int index = first;
			for (int i = 0; i < size; i++) {
				if (index>=elements.length) {
					index = 0;
				}
				if (element.equals(elements[i])) {
					return i;
				}
				index += 1;
			}
			return NOT_FOUND_ELEMENT;
		}else {
			int index = first;
			for (int i = 0; i < size; i++) {
				if (index>=elements.length) {
					index = 0;
				}
				if (elements[i]==null) {
					return i;
				}
				index += 1;
			}
			return NOT_FOUND_ELEMENT;
		}
	}
 
	@Override
	public void clear() {
		size = 0;
		first = 0;
		elements = (E[]) new Object[DEFAULT_CAPATICY];
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Capacity==").append(elements.length)
		.append(", size==").append(size)
		.append(", first==").append(first)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i!=0) {
				sb.append(", ");
			}
			sb.append(elements[i]);
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public int removeElement(E element) {
		int index = indexof(element);
		remove(index);
		return index;
	}
	
}
