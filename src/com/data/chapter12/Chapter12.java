package com.data.chapter12;

public class Chapter12 {
	public static void main(String[] args) {
		int[] arr = {2,2,4,5,5,3,6,2,6};
		bucketSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
	/**
	 * 桶排序,桶排序构造的数组每一个下标代表是
	 * 原数组中的数字，数组中的内容代表该数字
	 * 出现的次数
	 * @param arr
	 */
	public static void bucketSort(int[] arr){
		int max = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i]>max)
				max = arr[i];
		}
		int[] bucket = new int[max+1];
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int k = 0;
		for (int i = 0; i < bucket.length; i++) {
			for (int j = 1; j <= bucket[i]; j++) {
				arr[k] = i;
				k++;
			}
		}
		
	}
}
