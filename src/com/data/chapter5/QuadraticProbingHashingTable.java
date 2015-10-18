package com.data.chapter5;

/**
 * 平方探测散列表
 * @author zhangqd
 */
public class QuadraticProbingHashingTable<E> {
	private static final int DEFAULT_TABLE_SIZE = 11;
	/**
	 * The array of elements
	 */
	private HashEntry<E>[] array ;
	/**
	 * The number of occupied cells
	 */
	private int currentSize;
	
	public QuadraticProbingHashingTable(){
		this(DEFAULT_TABLE_SIZE);
	}
	
	public QuadraticProbingHashingTable(int size){
		allocateArray(size);
		makeEmpty();
	}
	
	public void makeEmpty(){
		currentSize = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}
	/**
	 * Insert into the hash table, if the item is
	 * already present,do nothing
	 * @param e
	 */
	public void insert(E e){
		int currentPos = findPos(e);
		if(isActive(currentPos))
			return;
		array[currentPos] = new HashEntry<E>(e, true);
		
		if(++currentSize > array.length/2)
			rehash();
	}
	
	public void remove(E e){
		int currentPos = findPos(e);
		if(isActive(currentPos))
			array[currentPos].isActive = false;
	}
	
	public boolean contains(E e){
		int currentPos = findPos(e);
		return isActive(currentPos);
	}
	
	public boolean isEmpty(){
		return currentSize==0;
	}
	
	public E find(E e){
		int currentPos = findPos(e);
		return isActive(currentPos)?array[currentSize].element:null;
		
	}
	
	private void allocateArray(int arraySize){
		array = new HashEntry[arraySize];
	}
	
	private boolean isActive(int currentPos){
		return array[currentPos]!=null && array[currentPos].isActive;
	}
	
	/**
	 * Method that performs quadratic probing resolution
	 * @param e the item to search for
	 * @return the position where the search terminates
	 */
	private int findPos(E e){
		int offset = 1;
		int currentPos = myhash(e);
		while(array[currentPos]!=null && !array[currentPos].element.equals(e)){
			currentPos += offset;//Compute i th probe
			offset+=2;
			if(currentPos>=array.length)
				currentPos -=array.length;
		}
		return currentPos;
	}
	/**
	 * Rehashing for quadratic probing hash table
	 */
	private void  rehash(){
		HashEntry<E>[] oldArray = array;
		//Create a new double-sized ,empty table
		allocateArray(nextPrime(2*oldArray.length));
		currentSize = 0;
		//Copy table over
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i]!=null && oldArray[i].isActive)
				insert(oldArray[i].element);
		}
	}
	
	private int myhash(E e){
		int hashVal = e.hashCode();
		hashVal %= array.length;
		if(hashVal<0)
			hashVal = hashVal + array.length;
		return hashVal;
	}
	
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
	
	private static class HashEntry<E>{
		public E element;
		/**
		 * false if marked deleted
		 */
		public boolean isActive;
		
		public HashEntry(E e){
			this(e, true);
		}
		public HashEntry(E e, boolean i){
			element = e;
			isActive = i;
		}
	}

}
