package com.datastructure.structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 * @author zhangqd
 *
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public void setEmpty(){
		root = null;
	}
	
	public  boolean isEmpty(){
		return root==null;
	}
	
	public boolean contains(E x){
		return contains(x, root);
	}
	
	/**
	 * Internal method to find an item in a subtree
	 * @param x
	 * @param t
	 * @return
	 */
	public E findMin(){
		if(isEmpty())
			throw new NullPointerException();
		return findMin(root).element;
	}
	/**
	 * Iterate findMax
	 * @param t
	 * @return
	 */
	public E findMax(){
		if(isEmpty())
			throw new NullPointerException();
		return findMax(root).element;
	}
	/**
	 * Inserts into a subtree
	 * @param x
	 */
	public void insert(E x){
		root = insert(x,root);
	}
	
	public void remove(E x){
		root = remove(x,root);
	}
	
	/**
	 * inorder
	 */
	public void printTree(){
		if(isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}
	/**
	 * 计算二叉树中节点个数
	 * @param t
	 * @return
	 */
	public int countNodes(BinaryNode<E> t){
		if(t==null)
			return 0;
		return 1+countNodes(t.left)+countNodes(t.right);
	}
	/**
	 * 计算二叉树中叶子节点个数
	 * @param t
	 * @return
	 */
	public int countLeaves(BinaryNode<E> t){
		if(t==null)
			return 0;
		else if(t.left!=null && t.right!=null)
			return 1;
		return countLeaves(t.left)+countLeaves(t.right);
	}
	/**
	 * 计算二叉树中满节点的个数
	 * @param t
	 * @return
	 */
	public int countFull(BinaryNode<E> t){
		if(t==null)
			return 0;
		int fullNode = (t.left!=null && t.right!=null)?1:0;
		return fullNode+countFull(t.left)+countFull(t.right);
		
	}
	/**
	 * 删除所有的树叶，并返回对根节点的引用
	 */
	public void removeLeaves(){
		if(isEmpty())
			throw new NullPointerException();
		root = removeLeaves(root);
	}
	
	private BinaryNode<E> removeLeaves(BinaryNode<E> t){
		if(t==null||(t.left==null && t.right==null))
			return null;
		t.left = removeLeaves(t.left);
		t.right = removeLeaves(t.right);
		return t;
	}
	/**
	 * 判断是否是二叉查找树，有问题
	 * @return
	 */
	public boolean isBinarySearchTree(){
		if(isEmpty())
			return true;
		return isBinarySearchTree(root,root.element,root.element);
	}
	/**
	 * 判断是否满足查找树的性质
	 * @return
	 */
	private boolean isBinarySearchTree(BinaryNode<E> t,E min, E max){
		if(t==null)
			return true;
		boolean isLeft = true ;
		boolean isRight = true;
		if(t.left!=null && isLeft){
			if(t.left.element.compareTo(max)<0)
				isLeft = isBinarySearchTree(t.left,min,t.left.element);
			else 
				isLeft = false;
		}
		if(t.right!=null && isRight){
			if(t.right.element.compareTo(min)>0)
				isRight = isBinarySearchTree(t.right,t.right.element,max);
			else 
				isRight = false;
		}
			
		return isLeft && isRight;
	}
	/**
	 * 二叉树的层次遍历
	 */
	public void layerTraverse(){
		layerTraverse(root);
	}
	
	private void layerTraverse(BinaryNode<E> t){
		if(t==null) {
			System.out.println("Empty tree");
			return;
		}
	Queue<BinaryNode<E>> queue = new LinkedList<BinaryNode<E>>();
	BinaryNode<E>  temp = t;
	BinaryNode<E> tempL;
	BinaryNode<E> tempR;
	queue.add(temp);
	while(!queue.isEmpty()){
		BinaryNode<E> e =queue.poll();
		System.out.println(e.element);
		tempL = e.left;
		tempR = e.right;
		if(tempL!=null)
			queue.add(tempL);
		if(tempR!=null)
			queue.add(tempR);
		}
	
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
	
	/**
	 * Recursive findMin
	 * @param t
	 * @return
	 */
	private BinaryNode<E> findMin(BinaryNode<E> t){
		if(t==null)
			return null;
		else if(t.left==null)
			return t;
		return findMin(t.left);
	}
	
	/**
	 * 使用后续遍历计算树的高度，树叶高度为0
	 * @param t
	 * @return
	 */
	public  int height(BinaryNode<E> t){
		if(t==null)
			return -1;
		return 1+Math.max(height(t.left), height(t.right));
	}
	
	private BinaryNode<E> findMax(BinaryNode<E> t){
		if(t!=null)
			while(t.right!=null)
				t = t.right;
		return t;
	}
	
	/**
	 * 
	 * @param x
	 * @param t
	 * @return the new root of the subtree
	 */
	private BinaryNode<E> insert(E x, BinaryNode<E> t){
		if(t==null)
			return new BinaryNode<E>(x,null,null);
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			t.left = insert(x,t.left);
		else if(compareResult>0)
			t.right = insert(x,t.right);
		else
			;  //Duplicate ,do nothing
		return t;
	}
	
	/**
	 * Internal method to remove from a subtree
	 * @param x the item to remove
	 * @param t the node that roots the subtree
	 * @return the new new root of the subtree
	 */
	private BinaryNode<E> remove(E x, BinaryNode<E> t){
		if(t==null)
			return t;
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			t.left = remove(x,t.left);
		else if(compareResult>0)
			t.right = remove(x,t.right);
		else if(t.left!=null && t.right!=null){ //Two Children
			t.element = findMin(t.right).element;//findMin the right subtree
			t.right = remove(t.element,t.right);
		}
		else
			t=(t.left!=null)?t.left:t.right;
		return t;
	}
	
	private void printTree(BinaryNode<E> t){
		if(t!=null){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	
	private static class BinaryNode<E>{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		
		public BinaryNode(E theElement){
			this(theElement, null,null);
		}
		
		public BinaryNode(E theElement, BinaryNode<E> lt, BinaryNode<E> rt){
			element = theElement;
			left = null;
			right = rt;
		}
		
	}
	
}
