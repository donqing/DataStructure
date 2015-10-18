package com.datastructure.structure;
/**
 * 确定性跳跃表(1-2-3deterministic skip list)
 * @author zhangqd
 */
public class DSL<E extends Comparable<? super E>> {
	
	private E infinity;
	
	private SkipNode<E> header;
	
	private SkipNode<E> bottom = null;
	
	private SkipNode<E> tail = null;
	
	public DSL(E inf){
		infinity = inf;
		bottom = new SkipNode<E>(null);
		bottom.right = bottom.down = bottom;
		tail = new SkipNode<E>(infinity);
		tail.right = tail;
		header = new SkipNode<E>(infinity,tail,bottom);
	}
	
	/**
	 * Find an item in the DSL
	 * @return
	 */
	public boolean contains(E e){
		SkipNode<E> current = header;
		bottom.element = e;
		for(;;){
			int compareResult = e.compareTo(current.element);
			if(compareResult < 0)
				current = current.down;
			else if(compareResult > 0)
				current = current.right;
			else
				return current != bottom;
		}
	}
	
	/*
	 * Insert an item into the DSL
	 */
	public void insert(E e){
		SkipNode<E> current = header;
		bottom.element = e;
		while(current!=bottom){
			while(current.element.compareTo(e)<0)
				current = current.right;
			//If gap size is 3 or at bottom level and 
			//must insert ,then promote middle element
			if(current.down.right.right.element.compareTo(current.element)<0){
				current.right = new SkipNode<E>(current.element,current.right,current.down.right.right);
				current.element = current.down.right.element;
			}
			else
				current = current.down;
		}
		//Raise height of DSL if necessary
		if(header.right!=tail)
			header = new SkipNode<E>(infinity,tail,header);
	}
	
	private static class SkipNode<E>{
		E element; //The data in the node
		SkipNode<E> right;  //RightLink
		SkipNode<E> down;   //Down link
		
		public SkipNode(E theElement){
			this(theElement,null,null);
		}
		
		public SkipNode(E theElement, SkipNode<E> rt, SkipNode<E> dt){
			element = theElement;
			right = rt;
			down = dt;
		}
	}
}
