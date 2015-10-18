package com.datastructure.structure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * MyTreeSet实现，迭代器使用二叉查找树
 * @author zhangqd
 *
 * @param <E>
 */

public class MyTreeSet<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	private int modCount = 0;
	
	public MyTreeSet(){
		root = null;
	}
	
	public void makeEmpty(){
		root = null;
		modCount++;
	}
	
	public boolean isEmpty(){
		return root==null;
	}
	
	public boolean contains(E x){
		return contains(x,root);
	}
	
	public void remove(E x){
		root = remove(x,root);
	}
	
	public E findMin(){
		if(isEmpty())
			throw new NullPointerException();
		else
			return findMin(root).element;
	}
	
	public E findMax(){
		if(isEmpty())
			throw new NullPointerException();
		else 
			return findMax(root).element;
	}
	
	public void insert(E x){
		root = insert(x,root,null);
	}
	
	public void printTree(){
		if(isEmpty())
			System.out.println("Empty Tree");
		else
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> t){
		if(t!=null){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	
	private BinaryNode<E> remove(E x, BinaryNode<E> t){
		if(t==null)
			return null;
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			t.left = remove(x,t.left);
		else if(compareResult>0)
			t.right = remove(x, t.right);
		else if(t.left!=null && t.right!=null){
			t.element = findMin(t.right).element;
			t.right = remove(t.element,t.right);
		}
		else{
			modCount++;
			BinaryNode<E> oneChild;
			oneChild = (t.left!=null?t.left:t.right);
			oneChild.parent = t.parent;
			t = oneChild;
		}
			return t;
			
	}
	//二叉搜索树实现
	private BinaryNode<E> insert(E x, BinaryNode<E> t,BinaryNode<E> pt){
		if(t==null){
			modCount++;
			t = new BinaryNode(x,null,null,pt);
			return t;
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			t.left = insert(x,t.left,t);
		else if(compareResult>0)
			t.right = insert(x,t.right,t);
		else
			;//Duplicate
		return t;
		
	}
	private boolean contains(E x, BinaryNode<E> t){
		if(t==null)
			return false;
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			return contains(x,t.left);
		else if(compareResult>0)
			return contains(x,t.right);
		else
			return true;
	}
	private BinaryNode<E> findMin(BinaryNode<E> t){
		if(t==null)
			return null;
		else if(t.left==null)
			return t;
		else
			return findMin(t.left);
	}
	private BinaryNode<E> findMax(BinaryNode<E> t){
		if(t==null)
			return null;
		else if(t.right==null)
			return t;
		return findMax(t.right);
	}
	
	private static class BinaryNode<E>{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		BinaryNode<E> parent;
		public BinaryNode(E element){
			this(element, null,null,null);
		}
		public BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right,BinaryNode<E> parent){
		this.element = element;
		this.left = left;
		this.right = right;
		this.parent = parent;
		}
	}
	
	public Iterator<E> iterator(){
		return new MyTreeSetIterator();
	}
	private class MyTreeSetIterator implements Iterator<E>{
		
		private BinaryNode<E> current = findMin(root);
		private BinaryNode<E> previous ;
		private int expectedCount=modCount;
		private boolean okToRemove = false;
		private boolean atEnd = false;
		
		@Override
		public boolean hasNext() {
			return !atEnd;
		}

		@Override
		public E next() {
			if(expectedCount!=modCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			E nextItem = current.element;
			previous = current;
			if(current.right!=null){
				current = findMin(current.right);
			}else{//find ancestor that it is left of
				BinaryNode<E> child = current;
				current = current.parent;
			while(current!=null && current.left!=child){
				child = current;
				current= current.parent;
			}
			if(current==null)
				atEnd = true;
			}
			okToRemove = true;
			return null;
		}

		@Override
		public void remove() {
			if(expectedCount!=modCount)
				throw new ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			MyTreeSet.this.remove(previous.element);
			okToRemove = false;
		}
		
	}
}
