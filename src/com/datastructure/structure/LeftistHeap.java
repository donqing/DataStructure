package com.datastructure.structure;
/**
 * 左式堆,链式结构实现,构造方法
 * buildHeap()?
 * @author zhangqd
 */
public class LeftistHeap<E extends Comparable<? super E>> {
	
	private Node<E> root;
	
	public LeftistHeap(){
		this(null);
	}
	
	public LeftistHeap(Node<E> root){
		this.root = root;
	}
	
	/**
	 * 合并两个左式堆
	 * @param rhs
	 */
	public void merge(LeftistHeap<E> rhs){
		if(this==rhs)
			return;
		root = merge(root, rhs.root);
		rhs.root = null;
	}
	
	public void insert(E e){
		root = merge(new Node<E>(e), root);
	}
	/**
	 * Design self
	 * @return
	 */
	public E findMin(){
		if(root ==null)
			return null;
		return root.element;
	}
	
	public E deleteMin(){
		if(isEmpty())
			throw new NullPointerException();
		E minItem = root.element;
		root = merge(root.left, root.right);
		return minItem;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	private Node<E> merge(Node<E> h1, Node<E> h2){
		if(h1==null)
			return h2;
		if(h2==null)
			return h1;
		if(h1.element.compareTo(h2.element)<0)
			return merge1(h1,h2);
		else 
			return merge1(h2,h1);
	}
	/**
	 * Internal method to merge two roots
	 * Assumes trees are not empty, and h1's root contains smallest item
	 * @param h1
	 * @param h2
	 * @return
	 */
	private Node<E> merge1(Node<E> h1, Node<E> h2){
		if(h1.left==null)
			h1.left = h2;
		else{
			h1.right = merge(h1.right,h2);
			if(h1.left.npl<h1.right.npl)
				swapChildren(h1);
			h1.npl = h1.right.npl+1;
		}
		return h1;
	}
	
	private void swapChildren(Node<E> t){
		Node<E> t1 = t.left;
		t.left = t.right;
		t.right = t1;
	}
	private static class Node<E>{
		//The data in the node
		E element;
		Node<E> left;
		Node<E> right;
		//Null path length,零路径长
		int npl;
		
		Node(E theElement){
			this(theElement,null,null);
		}
		
		Node(E theElement, Node<E> lt, Node<E> rt){
			element = theElement;
			left = lt;
			right = rt;
		}
	}
}
