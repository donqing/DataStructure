package com.datatstructure.chapter3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PrintLots {
	public static void main(String[] args) {
		List<Integer> L = new ArrayList<>();
		List<Integer> P = new ArrayList<>();
		L.add(2);
		L.add(8);
		L.add(9);
		L.add(7);
		L.add(3);
		L.add(5);
		L.add(7);
		P.add(2);
		P.add(3);
		P.add(4);
		P.add(7);
		printLots(L,P);
		System.out.println("-----");
//		printLots2(L,P);
	}
	//打印出P所指定的位置上的元素，如P=1,2。打印出L中第一，第二个元素,时间复杂度O(N),可以写个计数器进行判断
	public static <E> void printLots(List<E> L, List<Integer> P){
		Iterator<E> iterL = L.iterator();
		Iterator<Integer> iterP = P.iterator();
		int start = 0;
		int index = 0;
		int count = 0;
		E itemL = null;
		while(iterP.hasNext()){
			index = iterP.next();
			while(iterL.hasNext() && start < index){
				start++;
				itemL = iterL.next();
			}
			//如果L中没有当前指定位置的元素
			if(iterL.hasNext())
			System.out.println(itemL);
		}
		System.out.println("Time complexity: "+count);
	}
	//这个有问题，如果超过话，会打印出最后一个
	public static <E> void printLots2(List<E> L, List<Integer> P){
		Iterator<E> iteratorL = L.iterator();
		Iterator<Integer> iteratorP = P.iterator();
		int start = 0;
		E itemL = null;
		Integer itemP = 0;
		while(iteratorL.hasNext()&&iteratorP.hasNext()){
			itemP = iteratorP.next();
			while(start<itemP&&iteratorL.hasNext()){
				start++;
				itemL = iteratorL.next();
			}
			System.out.println(itemL);
		}
		
	}	
	
}
