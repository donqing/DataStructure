package com.datastructure.chapter2;

import java.util.List;

public  class Demo {
	public static void main(String[] args) {
		
	}
	
	public static void removeEvens(List<Integer> list){
		for (Integer x:list) {
			if(x%2==0)
				list.remove(x);
		}
	}
	//
	public static boolean findElement(int[][] array, int element){
		boolean find = false;
		int length = array.length;
		int ele = array[0][length-1];
		int[][] visited = new int[length][length];
		visited[0][length-1] = 1;
		int i = 0;
		int j = length - 1;
		while(i<=length-1&&j>=0){
			if(array[i][j]==element){
				find = true;
				break;
			}
			else if(element<array[i][j]){
				j--;
				//判断是否已经访问过
				if(j<0||visited[i][j]==1){
					break;
				}
				visited[i][j]=1;
			}
			else if(element>array[i][j]){
				i++;
				if(i>=length||visited[i][j]==1){
					break;
				}
				visited[i][j]=1;
			}
		}
		return find;
	}
	
	//找出数组中出现次数超过(>)N/2次的元素，肯定只有一个
	public static int findPivot(int[] array){
		if(array == null || array.length == 0) return -1;
		if(array.length == 1) return array[0];
		int[] pivot = new int[array.length];
		int k = 0,j=0,i=0;
		while(i<array.length){
			j = i+1;
			if(j<array.length && array[i]==array[j]){
				pivot[k] = array[j];
				k++;
				i = j+1;
			}
			else if(j<array.length && array[i]!=array[j]){
				i = j+1;
			}
			//当n为奇数时，该怎么处理？
			else if(j == array.length){
//				pivot[k] = array[i];
//				k++;
				i++;
			}
		}
		int[] pivot2 = new int[k];
		for (int l = 0; l < pivot2.length; l++) {
			pivot2[l] = pivot[l];
		}
		return findPivot(pivot2);
	}
	
	public static int findPivot2(int[] array){
		int count = 1;
		int pivot = array[0];
		for (int i = 1; i < array.length; i++) {
			if(array[i]==pivot)
				count++;
			else{
				count--;
				if(count<0){
					pivot = array[i];
					count = 1;
				}
			}
		}
		if(count == 0) return -1;
		return pivot;
	}
	
	
	//求一个数的二进制表示形式
	public static String toBinary(int num){
		if(num==0) return "0";
		StringBuffer strBinary = new StringBuffer();
		
		if(num > 0){
			while(num!=0){
				int bit = num % 2;
				num = num / 2;
				strBinary.append(bit);
			}
			return strBinary.reverse().toString();
		}
		else{
			return Integer.toBinaryString(num);
		}
	}
	
	public static boolean isPrim(int N){
		if(N == 1) return true;
		boolean isPrim = true;
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if(N % i ==0){
				isPrim = false;
				break;}
		}
		return isPrim;
	}
	
	
	
	//在方法中创建一个实例对象，返回该对象的引用，如果有外部引用它，则它不会被释放，该对象的
	//的局部引用失效，如b
	//若返回的是基本类型变量，基本类型变量是值传递，方法结束后，该变量不再存在，返回的是它的的一个副本
	/**
	 *返回值无外乎两种，一种是基本类型、另一种就是引用。
	 *基本类型的返回，就是把局部变量的值拷贝到接收返回值的那个变量里，所以局部变量生存周期结束也没有关系。
	 *返回引用的话，就是把局部变量中所存的引用值拷贝到了接收返回值的变量里，
	 *这样子接收返回值的那变量也引用了相同的对象，但因为java的对象都是new出来的，
	 *所以对象都在堆里，所以也没有关系。
	 * @param array
	 * @return
	 */
	public static int[] getArray(int[] array){
		int[] b = new int[array.length];
		int a = 1;
		for (int i = 0; i < array.length; i++) {
			b[i] = array[i];
		}
		
		return b;
	}
	
	
	public static int minSubSum(int[] array){
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			int thisSum = 0;
			for (int j = i; j < array.length; j++) {
				thisSum = thisSum + array[j];
				if(thisSum < minSum)
					minSum = thisSum;
			}
		}
		return minSum;
	}
	//最小子序列和O(N)时间复杂度
	public static int minSubSum2(int[] array){
		int minSum = Integer.MAX_VALUE;
		int thisSum = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i]<=0){
				thisSum += array[i];
				if(thisSum < minSum)
				minSum = thisSum;
			}
			else{
				thisSum = array[i];
				if(thisSum < minSum)
					minSum = thisSum;
				thisSum = 0;
			}
		}
		return minSum;
	}
	//最小正子序列和,即为求最小正整数
	public static int minSubSum3(int[] array){
		int minSum = Integer.MAX_VALUE;
		int thisSum = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i]<=0){
				thisSum = 0;
				continue;
			}
			thisSum = array[i];
			if(thisSum < minSum)
				minSum = thisSum;
		}
		return minSum;
	}
	//最大子序列乘积,序列中没有0,先判断有多少负数
	public static int maxSubMut(int[] array){
		if(array==null) return 0;
		if(array.length==1) return  array[0];
		int maxMut = 1;
		int maxLeft = 1;
		int maxRight = 1;
		int negative = 0;
		int k = 0;
		int[] negativeDiv = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			if(array[i] < 0){
				negative++;
				negativeDiv[k] = i;
				k++;
			}
		}
		//有偶数个负数
		if(negative%2==0){
			for (int i = 0; i < array.length; i++) {
				maxMut *=array[i];
			}
		}
		else{
			int j = negativeDiv[k-1];
			for (int i = 0; i < j; i++) {
				maxLeft = maxLeft*array[i];
			}
			for (int i = j+1; i < array.length; i++) {
				maxRight = maxRight * array[i];
			}
			maxMut = Math.max(maxLeft, maxRight);
		}
		return maxMut;
	}
	
	
	public static int maxSubSum(int[] array){
		int maxSum = 0;
		int thisSum = 0;
		for (int i = 0; i < array.length; i++) {
			 if(array[i] <= 0)
				thisSum = 0;
			 else{
				 thisSum += array[i]; 
				 if(thisSum > maxSum)
				 maxSum= thisSum;
			 }
		}
		return maxSum;
	}	
	
	public static int maxSubSum2(int[] array){
		int maxSum = 0;
		int thisSum = 0;
		for (int i = 0; i < array.length; i++) {
			 thisSum+=array[i];
			 if(thisSum > maxSum)
				 maxSum = thisSum;
			 else if(thisSum<0)
				 thisSum = 0;
		}
		return maxSum;
	}
		
}