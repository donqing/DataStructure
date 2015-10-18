package com.datastructure.structure;

public class MyAVLTree<E extends Comparable<? super E>> {
	
	private AvlNode<E> root;
	
	private int height;
	
	/**
	 * Returns the height of node t,or -1,if null
	 * @param t
	 * @return
	 */
	private int height(AvlNode<E> t){
		return t==null?-1:t.height;
	}
	/**
	 * Internal method to insert into a subtree
	 * @param e the item to insert
	 * @param t the node that roots the subtree
	 * @return  the new root of the subtree
	 */ 
	private AvlNode<E> insert(E e, AvlNode<E> t){
		if(t==null)
			return new AvlNode<E>(e,null,null);
		int compareResult = e.compareTo(t.element);
		if(compareResult<0){
			t.left = insert(e, t.left);
			if(height(t.left)-height(t.right)==2){
				if(e.compareTo(t.left.element)<0)
					t = rotateWithLeftChild(t);
				else 
					t = doubleWithLeftChild(t);
			}
		}
		else if(compareResult>0){
			t.right = insert(e, t.right);
			if(height(t.right)-height(t.left)==2){
				if(e.compareTo(t.right.element)>0)
					t = rotateWithRightChild(t);
				else
					t = doubleWithRightChild(t);
			}
		}
		else 
			;//Duplicate ,do nothing
		t.height = Math.max(height(t.left), height(t.right))+1;
		return t;
	}
	/**
	 * Rotates binary tree node with left child
	 * For AVL tree,this is a single rotation for case1
	 * (对左儿子的左子树进行插入)
	 * Update heights,then return new root
	 * @param k2
	 * @return
	 */
	private AvlNode<E> rotateWithLeftChild(AvlNode<E> k2){
		AvlNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right))+1;
		k1.height = Math.max(height(k1.left), k2.height)+1;
		return k1;
	}
	
	/**
	 * Rotate binary tree node with right child
	 * For AVL tree, this is a single rotation for case4
	 *(对右儿子的右子树进行一次插入)
	 *Update heights ,then return new root
	 * @param k2
	 * @return
	 */
	private AvlNode<E> rotateWithRightChild(AvlNode<E> k1){
		AvlNode<E> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k2.right))+1;
		k2.height = Math.max(height(k2.right), k1.height)+1;
		return k2;
	}
	
	/**
	 * Double rotate binary tree node:first left child
	 * with its right child;then node k3 with new left child
	 * For AVL trees, this is a double rotation for case2
	 * 对a的左儿子的右子树进行一次插入
	 * @param k3
	 * @return
	 */
	private AvlNode<E> doubleWithLeftChild(AvlNode<E> k3){
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	
	/**
	 *右-左双双旋转
	 *对a的右儿子的左子树进行一次插入
	 * @param k1
	 * @return
	 */
	private AvlNode<E> doubleWithRightChild(AvlNode<E> k1){
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithLeftChild(k1);
	}
	
	private static class AvlNode<E>{
		E element;
		AvlNode<E> left;
		AvlNode<E> right;
		int height;
		AvlNode(E theElement){
			this(theElement, null,null);
		}
		AvlNode(E theElement, AvlNode<E>lt, AvlNode<E>rt){
			element = theElement;
			left = lt;
			right = rt;
			height = 0;
		}
	}

}
