package com.zbowen;

public interface IDynamicArray<E> {

	/**
	 * 
	 * @return 元素的数量
	 */
	int size();

	/**
	 * 
	 * @return 是否为空
	 */
	boolean isEmpty();

	/**
	 * 
	 * @param element
	 * @return 是否包含某个元素
	 */
	boolean contains(E element);

	/**
	 * 添加元素到最后面
	 * @param element
	 */
	void add(E element);

	/**
	 * 
	 * @param index
	 * @return 返回index位置对应的元素
	 */
	E get(int index);

	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return
	 */
	E set(int index, E element); 

	/**
	 * 往index位置添加元素
	 * @param index
	 * @param element
	 */
	void add(int index, E element);

	/**
	 * 删除index位置对应的元素
	 * @param index
	 * @return
	 */
	E remove(int index);

	/**
	 * 查看元素的位置
	 * @param element
	 * @return
	 */
	int indexof(E element);

	/**
	 * 清除所有元素
	 */
	void clear();

}
