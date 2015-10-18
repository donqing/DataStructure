package com.datastructure.structure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *带有头结点和尾结点的双链表 
 * @author zhangqd
 * @param <E>
 */
public class MyLinkedList<E> implements Iterable<E> {
	
	private int theSize;
	/**
	 * modCount代表自从构造以来对链表所做的改变的次数。 每次对add 或 remove的调用都将更新modCount.
	 * 其想法在于，当一个迭代器被建立时，它将存储集合的modCount.每次对一个迭代器方法(next或remove)
	 * 的调用，都将用该链表内的当前modCount检测在迭代器内存储的modCount,并且当这两个计数不匹配时
	 * 抛出一个ConcurrentModificationException异常
	 * 
	 */
	private int modCount = 0;
	
	private Node<E> beginMarker;
	
	private Node<E> endMarker;
	
	public MyLinkedList(){
		clear();
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return theSize==0;
	}
	
	public boolean add(E element){
		add(size(),element);
		return true;
	}
	
	public void add(int idx, E element){
		addBefore(getNode(idx), element);
	}
	
	public E get(int idx){
		return getNode(idx).element;
	}
	/**
	 * Changes the size of this collection to zero
	 */
	public void clear(){
		beginMarker = new Node<E>(null,null,null);
		endMarker = new Node<E>(null,beginMarker, null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}
	
	public E set(int idx, E newVal){
		return set(getNode(idx), newVal);
	}
	
	private E set(Node<E> p, E element){
		E oldVal = p.element;
		p.element = element;
		return oldVal;
	}
	
	public int getIndex(E element){
		Node<E> p = beginMarker.next;
		int index = 0;
		while(p.next!=endMarker && p.element != element){
			index++;
		}
		if(index>=theSize)
			throw new NoSuchElementException();
		return index;
	}
	
	public E remove(int idx){
		return remove(getNode(idx));
	}
	
	/**
	 * Removes from this list all of its elements contained in the 
	 * specified collection(Iterable).
	 * @param items
	 */
	public void removeAll(Iterable<? extends E> items){
		E item, element;
		Iterator<? extends E> iterItems = items.iterator();
		while(iterItems.hasNext()){
			item = iterItems.next();
			Iterator<? extends E> iter = this.iterator();
			while(iter.hasNext()){
				element = iter.next();
				if(item.equals(element))
					iter.remove();
			}
		}
	}
	
	public void addFirst(E element){
		addBefore(beginMarker.next,element);
	}
	
	public void addLast(E element){
		addBefore(endMarker, element);
	}
	
	public void removeFirst(){
		 remove(beginMarker.next);
	}
	
	public void removeLast(){
		remove(endMarker.prev);
	}
	
	public E getFirst(){
		return get(0);
	}
	
	public E getLast(){
		return get(theSize-1);
	}
	
	/**
	 * Adds an item to this collection at specified position p.
	 * Items at or after that position slide one position higher.
	 * @param p Node to add before
	 * @param element  any object
	 */
	private void addBefore(Node<E> p, E element){
		Node<E> newNode = new Node<E>(element, p.prev,p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	/**
	 * Removes the object contained in Node 
	 * @param p the Node needs to be delete
	 * @return
	 */
	private E remove(Node<E> p){
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		return p.element;
	}
	/**
	 * Gets the node at the specified position in this list.
	 * @param idx
	 * @return
	 */
	private Node<E> getNode(int idx){
		Node<E> p;
		//有尾结点
		if(idx<0 || idx >size())
			throw new IndexOutOfBoundsException();
		if(idx < size()/2){
			p = beginMarker.next;
			for(int i = 0; i < idx ;i++)
				p = p.next;
		}else{//从尾结点开始搜索
			p = endMarker;
			for(int i = size(); i > idx ;i--)
				p = p.prev;
		}
		return p;
	}
	
	/**
	 * 双向迭代器
	 * @return
	 */
	public ListIterator<E> listIterator(){
		return new LinkedItr(beginMarker);
	}
	
	/**
	 * 单向
	 */
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<E>{
		
		protected Node<E> current = beginMarker.next;
		protected int expectedModCount = modCount;
		protected boolean okToRemove = false;
		
		@Override
		public boolean hasNext() {
			return current!=endMarker;
		}

		@Override
		public E next() {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			E nextItem = current.element;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		@Override
		public void remove() {
			if(modCount!=expectedModCount)
				throw new ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount++;
		}
	}
	
	private class LinkedItr extends LinkedListIterator implements ListIterator<E>{
		
		public LinkedItr(Node<E> p){
			current = p.next;
		}
		
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return current.prev != beginMarker;
		}

		@Override
		public E previous() {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if(!hasPrevious())
				throw new NoSuchElementException();
			current = current.prev;
			E item  = current.element;
			okToRemove = true;
			return item;
		}
		@Override
		public void add(E e) {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			MyLinkedList.this.add(e);
			
		}
		@Override
		public void remove() {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			MyLinkedList.this.set(current.next, e);
			
			
		}
	}
	
	private static class Node<E>{
		//E是插入的元素类型，不是结点类型
		public E element;
		public Node<E> prev;
		public Node<E> next; 
		public Node(E element, Node<E> prev, Node<E> next){
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
	}
	
	@Override
	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			E e = it.next();
			sb.append(e == this ? "(this Collection)" : e);//?为什么要加个判断
//			sb.append(e);
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}

}
