package com.datastructure.structure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {
	
	private static final int DEFAULT_CAPACITY = 10;
	/**
	 * The size of the ArrayList (the number of elements it contains).
	 */
	private int theSize;
	
	private E[] theItems;
	
	/**
	 * 存储MyArrayList修改的次数
	 */
	private int modCount;
	
	public MyArrayList(){
		clear();
	}
	/**
	 * 清空MyArrayList
	 */
	public void clear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	/**
	 *Returns the number of elements in this list.
	 * @return
	 */
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	/**
	 * Trims the capacity of this <tt>ArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
	 */
	public void trimToSize(){
		modCount++;
		ensureCapacity(size());
	}
	
	/**
	 * Returns the element at the specified position in this list 
	 * @param index
	 * @return
	 */
	public E get(int index){
		if(index < 0 || index >= size())
			throw new ArrayIndexOutOfBoundsException();
		return theItems[index];
	}
	
	/**
	 * Replaces the element at the specified position in this list
	 * with the specified element 
	 * @param index
	 * @param element
	 */
	public E set(int index, E element){
		if(index<0||index>=size())
			throw new ArrayIndexOutOfBoundsException();
		//ArrayList中存放的是对象的引用，保存原有对象的引用
		//即old是原有对象的引用
		E old = theItems[index];
		//将新对象的引用添加到ArrayList中
		theItems[index] = element;
		return old;
	}
	
	/**
	 * Increases the capacity of this MyArrayList instance, if necessary, 
	 * to ensure that it can hold the number of elements specified by the
	 * minimum capacity of argument
	 * @param newCapacity the desired minimum capacity
	 */
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity){
		modCount++;
		if(newCapacity < theSize)
			return;
		E[] old = theItems;
		theItems = (E[]) new Object[newCapacity];
		for(int i = 0; i < size(); i++)
			theItems[i] = old[i];
	}
	
	/**
	 * Appends the specified element to the end of this list
	 * @param element
	 * @return
	 */
	public boolean add(E element){
		add(size(), element);
		return true;
	}
	
	/**
	 * Inserts the specified element at the specified position in
	 * this list.
	 * @param index
	 * @param element
	 */
	public void add(int index, E element){
		modCount++;
		if(theItems.length == size())
			ensureCapacity(size()*2+1);//modCount
		for(int i = theSize; i>index;i--)
			theItems[i] = theItems[i-1];
		theItems[index] = element;
		theSize++;
	}
	
	/**
	 * Appends all of the elements in the specified collection to 
	 * the end of this list.
	 * @param items
	 */
	public void addAll(Iterable<? extends E> items){
		Iterator<? extends E> iterator = items.iterator();
		while(iterator.hasNext())
			add(iterator.next());
	}
	
	/**
	 * Removes the element at the specified position in this list
	 * @param index
	 * @return
	 */
	public E remove(int index){
		modCount++;
		if(index<0||index>=size())
			throw new ArrayIndexOutOfBoundsException();
		E removedItem = theItems[index];
		for (int i = index; i < theItems.length-1; i++) {
			theItems[i] = theItems[i+1];
		}
		theSize--;
		return removedItem;
	}
	
	/**
	 * Returns true if this list contains the specified element
	 * else false
	 * @param o
	 * @return
	 */
	public boolean contains(Object o){
		return indexOf(o) >= 0;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 * @param o
	 * @return
	 */
	public int indexOf(Object o){
		if(o==null){
			for (int i = 0; i < theSize; i++) {
				if(theItems[i] == null)
					return i;
			}
		}else{
			for(int i = 0; i < theSize; i++){
				if(o.equals(theItems[i]))
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * 提供一个双向迭代器
	 * @return
	 */
	public ListIterator<E> listIterator(){
		return new ListItr(0);
	}
	
	/**
	 * 提供一个单向迭代器
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<E>{
		
		int expectedModCount = modCount;
		int current = 0;
		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public E next() {
			if(expectedModCount!=modCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			return theItems[current++];
		}

		@Override
		public void remove() {
			expectedModCount++;
			MyArrayList.this.remove(--current);
		}
	}
	/**
	 * 提供一个反向迭代器
	 * @return
	 */
	public Iterator<E> reverseIterator(){
		return new ArrayListReverseIterator();
	}
	
	private class ArrayListReverseIterator implements Iterator<E>{
		protected int current = theSize-1;
		protected int expectedModCount2 = modCount;
		public ArrayListReverseIterator(){
			
		}

		@Override
		public boolean hasNext() {
			return current >-1;
		}

		@Override
		public E next() {
			if(expectedModCount2 != modCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			return theItems[current--];
		}
		
		/**
		 * 删除从迭代器返回的最后一个元素，调用MyArrayList的删除方法
		 */
		@Override
		public void remove() {
			MyArrayList.this.remove(++current);
			expectedModCount2++;
		}
		
	}
	
	/**
	 * 提供双向迭代器类
	 * @author zhangqd
	 */
	private class ListItr extends ArrayListIterator implements ListIterator<E>{
		
		public ListItr(int index){
			current = index;
		}
		
		boolean backwards = false;
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return current>0;
		}
		
		
		@Override
		public boolean hasNext() {
			return super.hasNext();
		}


		@Override
		public E next() {
			if(expectedModCount!=modCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			backwards = false;
			return theItems[current++];
		}

		@Override
		public E previous() {
			if(expectedModCount!=modCount)
				throw new ConcurrentModificationException();
			if(!hasPrevious())
				throw new NoSuchElementException();
			backwards = true;
			return theItems[--current];
		}

		@Override
		public void remove() {
			if(expectedModCount!=modCount)
				throw new ConcurrentModificationException();
			if(backwards)
				MyArrayList.this.remove(current--);
			else{
				MyArrayList.this.remove(--current);
				expectedModCount++;
			}
		}


		@Override
		public int nextIndex() {
			return current;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return current-1;
		}

		@Override
		public void set(E e) {
			
			MyArrayList.this.set(current, e);
		}

		@Override
		public void add(E e) {
			//先使用，再加加
			MyArrayList.this.add(current++,e);
		}
		
	}
	
	@Override
	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			E e = it.next();
			sb.append(e == this ? "(this Collection)" : e);//?为什么要加个判断
//			sb.append(e);
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}

}
