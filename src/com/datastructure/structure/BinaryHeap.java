package com.datastructure.structure;
/**
 * 二叉堆
 * @author zhangqd
 *数组下标从1开始(其实从0开始还是从1开始无所谓，因为对外是
 *一种堆的数据结构，对外数组不可见）
 * @param <E>
 */
public class BinaryHeap<E extends Comparable<? super E>> {
	
	private static final int DEFAULT_CAPACITY = 10;
	//The heap array
	private E[] array;
	/**
	 * Number of elements in heap
	 */
	private int currentSize;
	
	public BinaryHeap(){
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity){
		array = (E[])new Object[capacity];
		currentSize = 0;
	}
	/**
	 * Construct the binary heap given an array of items
	 * @param items
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(E[] items){
		currentSize = items.length;
		array = (E[]) new Comparable[(currentSize+2)*11/10];
		int i = 1;
		for(E item:items)
			array[i++] = item;
		buildHeap();
	}
	
	/**
	 * Insert into the priority queue,maintaining heap order
	 * Duplicates are allowed
	 * @param e
	 */
	public void insert(E e){
		if(currentSize==array.length-1)
			enlargeArray(array.length*2+1);
		int hole = ++currentSize;
		for(;hole>1&&e.compareTo(array[hole/2])<0;hole /=2)
			array[hole] = array[hole/2];
		array[hole] = e;
	}
	/**
	 * Design self
	 * @return
	 */
	public E findMin(){
		if(array==null)
			return null;
		return array[1];
	}
	/**
	 * Remove the smallest item from the priority queue
	 * @return
	 */
	public E deleteMin(){
		if(isEmpty())
			throw new NullPointerException();
		E minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);
		return minItem;
	}
	/**
	 * Design self
	 * @return
	 */
	public boolean isEmpty(){
		return currentSize==0;
	}
	/**
	 * Design self
	 */
	public void makeEmpty(){
		for (int i = 1; i < array.length; i++) {
			array[i] = null;
		}
		currentSize = 0;
	}
	/**
	 * Internal method to percolate down in the heap
	 * @param hole the index at which the percolate begins
	 */
	private void percolateDown(int hole){
		int child;
		E tmp = array[hole];
		for(;hole*2<=currentSize;hole=child){
			child = hole*2;
			if(child!=currentSize&&array[child+1].compareTo(array[child])<0)
				child++;
			if(array[child].compareTo(tmp)<0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
	}
	/**
	 * Establish heap order property from an arbitray
	 * arrangement of items.Runs in linear time
	 */
	private void buildHeap(){
		for(int i = currentSize/2;i>0;i--)
			percolateDown(i);
	}
	
	private void enlargeArray(int newSize){
		if(newSize<DEFAULT_CAPACITY)
			return;
		E[] old = array;
		E[] newArray = (E[])new Object[newSize];
		for (int i = 1; i < old.length; i++) {
			newArray[i] = old[i];
		}
		array = newArray;
	}

}
