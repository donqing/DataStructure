package com.datastructure.structure;

import com.data.chapter5.QuadraticProbingHashingTable;

public class MyMap<K, V> {
	
	private QuadraticProbingHashingTable<Entry<K,V>> item;
	
	public MyMap(){
		item = new QuadraticProbingHashingTable<Entry<K,V>>();
	}
	
	public void put(K key, V val){
		Entry<K, V> entry = new Entry<K,V>(key, val);
		item.insert(entry);
	}
	
	public V get(K key){
		V val = (V) new Object();
		Entry<K,V> entry = new Entry<K, V>(key, val);
		entry = item.find(entry);
		return entry.value;
	}
	
	public boolean isEmpty(){
		return item.isEmpty();
	}
	
	public void makeEmpty(){
		item.makeEmpty();
	}
	
	private static class Entry<K, V>{
		K key;
		V value;
		
		Entry(K key, V val){
			this.key = key;
			this.value = val;
		}
		public int hashCode(){
			return key.hashCode();
		}
		public boolean equals(Object rhs){
			return rhs instanceof Entry && key.equals(((Entry<K, V>)rhs).key);
		}
	}

}
