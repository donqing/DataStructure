package com.data.chapter7;

public class Exercise7 {
	public static void main(String[] args) {
		Integer[] num = {9,12,8,5};
		heapSort(num,1,2);
		for(Integer nbr:num)
			System.out.print(nbr + " ");
		
	}
	
	public static <E extends Comparable<? super E>> void heapSort(E[] arr,int low , int high){
		if(low > high) return;
		if(arr==null || arr.length==0||arr.length==1 || low <0 || high>arr.length ) return;
		E[] tmp = (E[]) new Comparable[high-low+1];
		int lowTmp = low;
		for(int i = 0;i<tmp.length;i++)
			tmp[i] = arr[lowTmp++];
		heapSort(tmp);
		for(int i = low,j=0 ;i<=high;i++,j++){
			arr[i] = tmp[j];
		}
		tmp=null;
	}
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

	private static int leftChild(int pos){
		return 2*pos+1;
	}
	
	
	
	
	public static <E extends Comparable<? super E>> void insertionSort(E[] arr){
		if(arr==null || arr.length==0 || arr.length==1)
			return;
		E tmp = arr[0];
		for(int i = 1;i<arr.length;i++){
			if(arr[i].compareTo(arr[i-1])<0){
				 tmp = arr[i];
				 int j = i-1;
				while(j>=0){
					if(arr[j].compareTo(tmp)>0)
						arr[j+1] = arr[j];
					else break;
					j--;
				}
				arr[j+1] = tmp;
			}
			
		}
	}
	//形式更简单
	public static <E extends Comparable<? super E>> void insertionSort2(E[] array){
		int j = 0;
		for (int p = 1; p < array.length; p++) {
			E tmp = array[p];
			for(j=p;j>0&&tmp.compareTo(array[j-1])<0;j--)
				array[j] = array[j-1];
			array[j] = tmp;
		}
	}
}
