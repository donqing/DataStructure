package com.datastructure.structure;
/**
 * 二项队列
 * @author zhangqd
 */
public class BinomialQueue<E extends Comparable<? super E>> {
	
	private static final int DEFAULT_TREES = 1;
	
	private int currentSize;//items in priority queue
	
	//An array of tree nodes
	private Node<E>[] theTrees;
	
	public BinomialQueue(){
		
	}
	
	public BinomialQueue(E item){
		
	}
	
	/**
	 * Merge rhs into the priority queue
	 * Then rhs becomes empty.
	 * rhs must be different from this.
	 * @param rhs
	 */
	public void merge(BinomialQueue<E> rhs){
		if(this==rhs) //Avoid aliasing problems
			return ;
		currentSize += rhs.currentSize;
		if(currentSize>capacity()){
			int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
			expandTheTrees(maxLength+1);
		}
		Node<E> carry = null;
		for(int i = 0,j = 1;j<=currentSize;i++,j*=2){
			Node<E> t1 = theTrees[i];
			Node<E> t2 = i < rhs.theTrees.length ? rhs.theTrees[i]:null;
			int whichCase = t1 == null ? 0:1;
			whichCase += t2 == null ? 0:2;
			whichCase += carry == null ? 0:4;
			switch(whichCase){
			case 0: /*No trees*/
			case 1: /*Only this*/
				break;
			case 2: /*Only rhs*/
				theTrees[i] = t2;
				rhs.theTrees[i] = null;
				break;
			case 4:/*Only carry*/
				theTrees[i] = carry;
				carry = null;
				break;
			case 3:/*this and rhs*/
				carry = combineTrees(t1,t2);
				theTrees[i] = rhs.theTrees[i]=null;
				break;
			case 5:/*this and carry*/
				carry = combineTrees(t1,carry);
				theTrees[i] = null;
				break;
			case 6:/*rhs and carry*/
				carry = combineTrees(t2,carry);
				rhs.theTrees[i] = null;
				break;
			case 7:/*All three*/
				theTrees[i] = carry;
				carry = combineTrees(t1,t2);
				rhs.theTrees[i] = null;
				break;
			}
		}
		for(int k = 0; k < rhs.theTrees.length;k++){
			rhs.theTrees[k] = null;
		}
		rhs.currentSize = 0;
		
	}
	
	public void insert(E e){
		
	}
	
	public E findMin(){
		if(isEmpty())
			throw new NullPointerException();
		int minIndex = findMinIndex();
		E minItem = theTrees[minIndex].element;
		return minItem;
	}
	/**
	 * Remove the smallest item from the priority queue
	 * @return
	 */
	public E deleteMin(){
		if(isEmpty())
			throw new NullPointerException();
		int minIndex = findMinIndex();
		E minItem = theTrees[minIndex].element;
		Node<E> deletedTree = theTrees[minIndex].leftChild;
		//Construct H''
		BinomialQueue<E> deletedQueue = new BinomialQueue<E>();
		deletedQueue.expandTheTrees(minIndex+1);
		deletedQueue.currentSize = (1<<minIndex)-1;
		for(int j = minIndex-1;j>=0;j--){
			deletedQueue.theTrees[j]= deletedTree;
			deletedTree = deletedTree.nextSibling;
			deletedQueue.theTrees[j].nextSibling = null;
		}
		//Construct H'
		theTrees[minIndex] = null;
		currentSize -= deletedQueue.currentSize+1;
		merge(deletedQueue);
		return minItem;
		
	}
	
	public boolean isEmpty(){
		return currentSize==0;
	}
	
	public void makeEmpty(){
		for (int i = 0; i < theTrees.length; i++) {
			theTrees[i]=null;
		}
		currentSize = 0;
	}
	
	private void expandTheTrees(int newNumTrees){
		
	}
	
	/**
	 * Returns the result of merging equal-sized t1 and t2
	 * @param t1
	 * @param t2
	 * @return
	 */
	private Node<E> combineTrees(Node<E> t1, Node<E> t2){
		if(t1.element.compareTo(t2.element)>0)
			return combineTrees(t2,t1);
		t2.nextSibling = t1.leftChild;
		t1.leftChild = t2;
		return t1;
	}
	
	private int capacity(){
		return (1<<theTrees.length)-1;
	}
	
	private int findMinIndex(){
		if(isEmpty())
			throw new NullPointerException();
		E minItem = theTrees[0].element;
		int k = 0;
		for(int i = 1;i<currentSize;i++){
			if(theTrees[i].element.compareTo(minItem)<0){
				k = i;
			}
		}
		return k;
	}
	private static class Node<E>{
		E element;
		Node<E> leftChild;//Left child
		Node<E> nextSibling;//Right child
		Node(E theElement){
			
		}
		Node(E theElement, Node<E> lt, Node<E> nt){
			element = theElement;
			leftChild = lt;
			nextSibling = nt;
		}

	}
}
