package com.data.chapter5;

import java.util.LinkedList;
import java.util.List;

/**
 *分离链接法解决散列冲突 
 * @author zhangqd
 */
public class SeparateChainingHashTable<E> {
	
	private static final int DEFAULT_TABLE_SIZE=10;
	
	private List<E>[] theLists;
	
	private int currentSize;
	
	public SeparateChainingHashTable(){
		this(DEFAULT_TABLE_SIZE);
	}
	
	public SeparateChainingHashTable(int size){
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<E>();
		}
	}
	
	public void insert(E x){
		List<E> whichList = theLists[myhash(x)];
		if(!whichList.contains(x)){
			whichList.add(x);
			currentSize++;
			if(currentSize>theLists.length)
				rehash();
		}
	}
	
	public void remove(E x){
		List<E> whichList = theLists[myhash(x)];
		if(whichList.contains(x)){
			whichList.remove(x);
			currentSize--;
		}
	}
	/**
	 * Finds an item in the hash table
	 * @param x
	 * @return
	 */
	public boolean contains(E x){
		List<E> whichList = theLists[myhash(x)];
		return whichList.contains(x);
	}
	
	public void makeEmpty(){
		for(int i = 0; i<theLists.length;i++)
			theLists[i].clear();
		//TODO
		currentSize= 0;
	}
	/**
	 * Rehashing for SeparateChainingHashTable
	 */
	private void rehash(){
		List<E>[] oldLists = theLists;
		theLists = new List[nextPrime(2*theLists.length)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<E>();
		}
		currentSize = 0;
		for (int i = 0; i < oldLists.length; i++) {
			for (E item : oldLists[i]) {
				insert(item);
			}
		}
	}
	
	private int myhash(E x){
		int hashVal = x.hashCode();
		hashVal %= theLists.length;
		if(hashVal<0)
			hashVal = hashVal + theLists.length;
		return hashVal;
	}
	
	/**
	 * 找出大于n的第一个素数
	 * @param n
	 * @return
	 */
	private static int nextPrime(int n){
		if(isPrime(n))return n;
		int prime = n;
		int i = n+1;
		while(i<2*n){
			if(isPrime(i)){
			prime = i;
			break;
			}
			i++;
		}
		return prime;
	}
	
	private static boolean isPrime(int n){
		for (int i = 0; i <=n /2; i++) {
			if( n % i==0)
				return false;
		}
		return true;
	}
	
	
}
