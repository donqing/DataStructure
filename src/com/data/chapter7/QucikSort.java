package com.data.chapter7;
/**
 * Quick sort algorithm
 * Quick selection
 * @author zhangqd
 *
 */
public class QucikSort {
	
	
	public static void main(String[] args) {
		Integer[] num = {11,3,8,9,23,21,14,16,29,36,26,43,41,81};
		System.out.println(quickSelect(num,2));
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i]+" ");
		}
		quickSort(num);
		System.out.println("===");
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i]+" ");
		}
	}
	/**
	 * 小数组截止长度,较小的数组不用快速排序
	 */
	private static int CUTOFF=10;
	
	/**
	 * Quicksort algorithm
	 * @param arr
	 */
	public static <E extends Comparable<? super E>> 
	void quickSort(E[] arr){
		quickSort(arr,0,arr.length-1);
	}
	
	private static <E extends Comparable<? super E>>
	void quickSort(E[] arr, int left, int right){
		if(left+CUTOFF<=right){
			E pivot = median3(arr,left,right);
			int i = left, j = right -1;
			for(;;){
				while(arr[++i].compareTo(pivot)<0){}
				while(arr[--j].compareTo(pivot)>0){}
				if(i<j)
					swapReferences(arr,i,j);
				else 
					break;
			}
			//Restore pivot
			swapReferences(arr,i,right-1);
			quickSort(arr,left,i-1);
			quickSort(arr,i+1,right);
		}
		else
			insertionSort(arr);
	}
	
	/**
	 * Insertion sort
	 * @param array
	 */
	private static <E extends Comparable<? super E>> void insertionSort(E[] array){
		int j = 0;
		for (int p = 1; p < array.length; p++) {
			E tmp = array[p];
			for(j=p;j>0&&tmp.compareTo(array[j-1])<0;j--)
				array[j] = array[j-1];
			array[j] = tmp;
		}
	}
	/**
	 * Return median of left,center,and right
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	private static <E extends Comparable<? super E>>
	E median3(E[] arr, int left, int right){
		int center = (left+right)/2;
		if(arr[center].compareTo(arr[left])<0)
			swapReferences(arr,left,center);
		if(arr[right].compareTo(arr[left])<0)
			swapReferences(arr,left,right);
		if(arr[right].compareTo(arr[center])<0)
			swapReferences(arr,center, right);
		swapReferences(arr,right-1,center);
		return arr[right-1];
	}
	
	private static<E extends Comparable<? super E>> void swapReferences(E[] arr, int src, int des){
		E old = arr[src];
		arr[src] = arr[des];
		arr[des] = old;
	}
	/**
	 * Find the kth min in array
	 * @param arr
	 * @param k
	 * @return
	 */
	public static <E extends Comparable<? super E>> E quickSelect(E[] arr, int k){
		quickSelect(arr,0,arr.length-1,k);
		return arr[k-1];
	}
	/**
	 * Internal selection method that recursive calls
	 * Uses median of three partitioning and a cutoff of 10
	 * Places the kth smallest item in arr[k-1]
	 * @param arr
	 * @param left
	 * @param right
	 * @param k
	 */
	private static <E extends Comparable<? super E>>
	void quickSelect(E[] arr, int left, int right, int k){
		if(left+CUTOFF<=right){
			E pivot = median3(arr,left,right);
			int i = left,j = right-1;
			for(;;){
				while(arr[++i].compareTo(pivot)<0){}
				while(arr[--j].compareTo(pivot)>0){}
				if(i<j)
					swapReferences(arr,i,j);
				else
					break;
			}
			swapReferences(arr,i,right-1);//Restore pivot
			if(k<=i)
				quickSelect(arr,left,i-1,k);
			else if(k>i+1)
				quickSelect(arr,i+1,right,k);
		}
		else
			insertionSort(arr);
	}
	

}
