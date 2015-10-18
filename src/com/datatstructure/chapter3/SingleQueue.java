package com.datatstructure.chapter3;

import java.util.ArrayList;

/**
 *使用循环数组高效实现队列类 
 * @author zhangqd
 * @param <E>
 */
public class SingleQueue<E> {
	
	private int front, rear;
	private int maxSize;
	private ArrayList<E> elements;
	
	public SingleQueue(){
		this(10);
	}
	public SingleQueue(int s){
		maxSize = s;
		front = 0;
		rear = 0;
		elements = new ArrayList<E>(maxSize);
	}
	
	public void enqueue(E element){
		if(!isFull()){
			if(elements.size()<maxSize)
			elements.add(element);
			else
				elements.set(rear, element);
			rear = (rear+1)%maxSize;
		}
	}
	
	public E dequeue(){
		E temp = null;
		if(!isEmpty()){
			temp = elements.get(front);
			//如果front指向最后一个元素，循环使其指向第一个元素
			front = (front+1)%maxSize;
		}
		return temp;
	}
	
	public boolean isEmpty(){
		return front == rear;
	}
	
	public boolean isFull(){
		return (rear + 1)%maxSize == front;
	}
	
}
