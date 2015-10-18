package com.datastructure.structure;


/**
 *自顶向下伸展树（自顶向下伸展)
 * @author zhangqd
 *
 */
public class SplayTree<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	
	private BinaryNode<E> nullNode;
	
	private BinaryNode<E> header = new BinaryNode<E>(null);//For splay
	
	private BinaryNode<E> newNode = null; //used between different inserts
	
	public SplayTree(){
		nullNode = new BinaryNode<E>(null);
		nullNode.left = nullNode.right = nullNode;
		root = nullNode;
	}
	
	/**
	 * Internal method to perform a top-down splay
	 * @param e
	 * @param t
	 * @return
	 */
	private BinaryNode<E> splay(E e, BinaryNode<E> t){
		BinaryNode<E> leftTreeMax ,rightTreeMin;
		header.left = header.right = nullNode;
		leftTreeMax = rightTreeMin = header;
		nullNode.element = e; //Guarantee a match
		for(;;){
			if(e.compareTo(t.element) < 0){
				t = rotateWithLeftChild(t);
			if(t.left==nullNode)
				break;
			//Link Right
			rightTreeMin.left = t;
			rightTreeMin = t;
			t = t.left;
			}
			else if(e.compareTo(t.element) > 0){
				if(e.compareTo(t.right.element) > 0)
					t = rotateWithRightChild(t);
				if(t.right==nullNode)
					break;
				//Link left
				leftTreeMax.right = t;
				leftTreeMax = t;
				t = t.right;
			}
			else
				break;
		}
		leftTreeMax.right = t.left;
		rightTreeMin.left = t.right;
		t.left = header.right;
		t.right = header.left;
		return t;
		
	}
	
	/**
	 * Insert into the tree
	 * @param e
	 */
	public void insert(E e){
		if(newNode == null)
			newNode = new BinaryNode<E>(null);
		newNode.element = e;
		if(root == nullNode){
			newNode.left = newNode.right = nullNode;
			root = newNode;
		}
		else{
			root = splay(e,root);
			if(e.compareTo(root.element)<0){
				newNode.left = root.left;
				newNode.right = root;
				root.left = nullNode;
				root = newNode;
			}
			else{
				if(e.compareTo(root.element)>0){
					newNode.right = root.right;
					newNode.left = root;
					root.right = nullNode;
					root = newNode;
				}else{
					return;
				}
			}
		}
		newNode = null;
	}
	
	/**
	 * Remove from the tree
	 * @param e the item to remove
	 */
	public void remove(E e){
		BinaryNode<E> newTree;
		//If e if found, it will be at the root
		root = splay(e,root);
		if(root.element.compareTo(e)!=0)
			return; //Item not found;
		if(root.left==nullNode)
			newTree = root.right;
		else{
			//Find the maximum in the left subtree
			//Splay it to the root; and then attach right child
			newTree = root.left;
			newTree = splay(e,newTree);
			newTree.right = root.right;
		}
		root = newTree;
		
	}
	
	public E findMin(){
		if(root == null)
			return null;
		BinaryNode<E> temp = root;
		while(temp.left!=null){
			temp = temp.left;
		}
		return temp.element;
	}
	
	/**
	 * 私有递归方法
	 * @param root
	 * @return
	 */
	private E findMin(BinaryNode<E> root){
		if(root == null)
			return null;
		if(root.left==null)return root.element;
		return findMin(root.left);
	}
	
	public E findMax(){
		if(root == null)
			throw new NullPointerException();
		return findMax(root);
	}
	
	private E findMax(BinaryNode<E> root){
		if(root == null)
			return null;
		else if(root.right == null)
			return root.element;
		return findMax(root.right);
	}
	
	public boolean contains(E e){
		return contains(e,root);
	}
	
	private boolean contains(E e, BinaryNode<E> root){
		if(root == null)
			return false;
		else if(e.compareTo(root.element) < 0)
			return  contains(e,root.left);
		else if(e.compareTo(root.element)>0)
			return contains(e, root.right);
		else 
			return true;
	}
	/**
	 * 非递归形式
	 */
	private boolean contains2(E e, BinaryNode<E> root){
		if(root == null)
			return false;
		BinaryNode<E> tmp = root;
		boolean find = false;
		while(find == false &&tmp!=null){
			if(e.compareTo(tmp.element) < 0)
				tmp = tmp.left;
			else if(e.compareTo(tmp.element)>0)
				tmp = tmp.right;
			else
				find = true;
		}
		return find;
	}
	
	
	
	public void makeEmpty(){
		root = nullNode;
	}
	
	public boolean isEmpty(){
		return root == nullNode;
	}
	
	private BinaryNode<E> rotateWithLeftChild(BinaryNode<E> k2){
		BinaryNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}
	
	private BinaryNode<E> rotateWithRightChild(BinaryNode<E> k1){
		BinaryNode<E> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}
	
	
	private static class BinaryNode<E>{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		
		public BinaryNode(E theElement){
			this(theElement,null,null);
		}
		
		public BinaryNode(E theElement, BinaryNode<E> left, BinaryNode<E> right){
			element = theElement;
			this.left = left;
			this.right = right;
		}
		
	}
}
