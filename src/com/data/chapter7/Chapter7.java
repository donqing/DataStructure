package com.data.chapter7;
/**
 * Sorting Algorithms
 * Insertion sort,ShellSort,HeapSort
 * @author zhangqd
 *
 */
public class Chapter7<E> {
	public static void main(String[] args) {
		int[] num = {2,9,4,5,8,3,12};
		int[] firstHalf = new int[num.length/2];
		System.arraycopy(num, 0, firstHalf, 0, num.length/2);
		for (int i = 0; i < firstHalf.length; i++) {
			System.out.print(firstHalf[i]+" ");
		}
		int secondHalfLength = num.length-num.length/2;
		int[] secondHalf = new int[secondHalfLength];
		System.out.println();
		System.arraycopy(num, num.length/2, secondHalf, 0, secondHalfLength);
		for (int i = 0; i < secondHalf.length; i++) {
			System.out.print(secondHalf[i]+" ");
		}
	}
	/**
	 * Insertion sort
	 * @param array
	 */
	public static <E extends Comparable<? super E>> void insertionSort(E[] array){
		int j = 0;
		for (int p = 1; p < array.length; p++) {
			E tmp = array[p];
			for(j=p;j>0&&tmp.compareTo(array[j-1])<0;j--)
				array[j] = array[j-1];
			array[j] = tmp;
		}
	}
	/**
	 * ShellSort, using Shell's(poor)increments.
	 * @param array
	 */
	public static <E extends Comparable<? super E>> void shellSort(E[] array){
		int j ;
		for(int gap = array.length/2;gap>0;gap/=2){
			for(int i = gap;i<array.length;i++){
				E tmp = array[i];
				for(j = i; j>=gap&&tmp.compareTo(array[j-gap])<0;j-=gap)
					array[j] = array[j-gap];
				array[j] = tmp;
			}
		}
		
	}
	
	/**
	 * Standard heapSort
	 * @param arr
	 */
	public static <E extends Comparable<? super E>> void heapSort(E[] arr){
		for(int i = arr.length/2;i>=0;i--)
			percDown(arr,i,arr.length);  //buildMaxHeap
		for(int i = arr.length-1;i>0;i--){
			//delete Max
			swapReferences(arr,0,i);
			percDown(arr,0,i);
		}
	}
	/*
	 * Internal method for heapSort,design self
	 */
	private static <E extends Comparable<? super E>> void swapReferences(E[] arr, int src,int des){
		E old = arr[src];
		arr[src] = arr[des];
		arr[des] = old;
	}
	/**
	 * buildMaxHeap
	 * @param arr an array of Comparable items
	 * @param pos the position from which to percolate down
	 * @param size the logical size of the binary heap
	 */
	private static <E extends Comparable<? super E>> void percDown(E[] arr, int pos, int size){
		int child;
		E tmp = arr[pos];
		while(leftChild(pos)<size){
			child = leftChild(pos);
			if(child!=size-1&&arr[child].compareTo(arr[child+1])<0)
				child++;
			if(tmp.compareTo(arr[child])<0)
				arr[pos] = arr[child];
			else 
				break;
			pos = child;
		}
		arr[pos] = tmp;
		
	}
	
	/**
	 * Internal method for heapSort
	 * i the index of an item in the heap
	 * return the index of the left child
	 */
	private static int leftChild(int pos){
		return 2*pos+1;
	}

	
}
