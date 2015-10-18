package com.data.chapter7;
/**
 * MergeSort
 * @author zhangqd
 *
 */
public class MergeSort {
	public static void main(String[] args) {
		Integer[] num = {3,1,4,1,5,9,2,6};
		mergeSort(num);
		for (Integer integer : num) {
			System.out.print(integer+" ");
		}
	}
	
	/**
	 * 未使用递归
	 * @param arr
	 * @param tmp
	 * @param left
	 * @param right
	 */
	public static <E extends Comparable<? super E>> 
	void mergeSort2(E[] arr, E[] tmp, int left, int right){
		int n = arr.length;
		for(int subListSize=1;subListSize<n;subListSize*=2){
			int part1Start = 0;
			while(part1Start+subListSize<n){
				int part2Start = part1Start+subListSize;
				int part2End = Math.min(n-1, part2Start+subListSize-1);
				merge(arr,tmp,part1Start,part2Start,part2End);
				part1Start = part2End+1;
			}
		}
	}
	
	/**
	 * Mergesort algorithm
	 * @param arr an array of Comparable items
	 */
	public static <E extends Comparable<? super E>> void mergeSort(E[] arr){
		E[] tmpArr = (E[]) new Comparable[arr.length];
		mergeSort(arr,tmpArr,0,arr.length-1);
//		mergeSort2(arr,tmpArr,0,arr.length-1);
	}
	
	/**
	 * Internal method that makes recursive calls
	 * @param arr
	 * @param tmpArr
	 * @param left
	 * @param right
	 */
	private static <E extends Comparable<? super E>>
	void mergeSort(E[] arr, E[] tmpArr, int left, int right){
		if(left<right){
			int center = (left+right)/2;
			mergeSort(arr,tmpArr,left,center);
			mergeSort(arr,tmpArr,center+1,right);
			merge(arr,tmpArr,left,center+1,right);
		}
	}
	/**
	 * Merge two sorted halves of a subarray
	 * @param arr  an array of Comparable items
	 * @param tmpArr an array to place the merged result
	 * @param leftPos left-mosted index of the subarray
	 * @param rightPos the index of the start of the second half
	 * @param rightEnd the right-most index of the subarray
	 */
	private static <E extends Comparable<? super E>> 
	void merge(E[] arr, E[] tmpArr, int leftPos, int rightPos, int rightEnd){
		int leftEnd = rightPos-1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos +1;
		//Main loop
		while(leftPos<=leftEnd && rightPos<=rightEnd)
			if(arr[leftPos].compareTo(arr[rightPos])<0)
				tmpArr[tmpPos++] = arr[leftPos++];
			else
				tmpArr[tmpPos++] = arr[rightPos++];
		while(leftPos<=leftEnd)
			//Copy rest of first half
			tmpArr[tmpPos++] = arr[leftPos++];
		while(rightPos<=rightEnd)
			//Copy rest of right half
			tmpArr[tmpPos++] = arr[rightPos++];
		//Copy tmpArr back
		for(int i = 0;i<numElements;i++,rightEnd--)
			arr[rightEnd] = tmpArr[rightEnd];
	}

}
