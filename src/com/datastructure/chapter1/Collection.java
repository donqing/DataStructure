package com.datastructure.chapter1;

public class Collection<T> {
	
	private int size;
	
	private Object[] collection = new Object[size];
	
	public boolean isEmpty(){
		return this.size==0;
	}
	
	public void makeEmpty(){
		this.size = 0;
	}
	
	public void insert(T obj){
		
	}
	
	public T remove(int index){
		if (index < size) {
			T obj = (T) collection[index];
			size--;
			return obj;
		}
		else {
			return null;
		}
	}
	
}
