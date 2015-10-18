package com.datatstructure.chapter3;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

public class Chapter3 {
	public static void main(String[] args) {
		String exp = "1+(20^2)=";
		String postFix = infixtoPostfix2(exp);
		System.out.println(evalPostfix(postFix));
		System.out.println(evalPostfix2(postFix));
	}
	
	/**
	 * 计算后缀表达式的值,能够判断即两位以上的整数
	 * @param expression
	 */
	public static double evalPostfix2(String expression){
		Stack<Double> s = new Stack<Double>();
		Double a, b ,result = 0.0;
		boolean isNumber = false;
		int i = 0,j;
		while(i<expression.length()){
			if(expression.charAt(i)>='0'&&expression.charAt(i)<='9'){
				j = i;
				//每次都需要新创建一个对象添加进去
				StringBuffer sb = new StringBuffer();
				while(j<expression.length()&&expression.charAt(j)>='0'&&expression.charAt(j)<='9'){
					sb.append(expression.charAt(j));
					j++;
				}
				i = j-1;
				String num = sb.toString();
				result = Double.parseDouble(num);
				isNumber = true;
			}else{
				isNumber = false;
			}
			if(isNumber){
				s.push(result);
			}else{
				switch(expression.charAt(i)){
				case '+':a = s.pop();b = s.pop();
				s.push((a+b));break;
				case '-':a = s.pop();b = s.pop();
				s.push((a-b));break;
				case '*':a = s.pop();b = s.pop();
				s.push((a*b));break;
				case '/':a = s.pop();b = s.pop();
				s.push((a/b));break;
				case '^':a = s.pop();b = s.pop(); //b的a次幂
				s.push(Math.exp(a*Math.log(b)));break;
				}
			}
			i++;
		}
		return s.peek();
	}
	
	
	
	/**
	 * 计算后缀表达式的值
	 * @param expression
	 */
	public static double evalPostfix(String expression){
		Stack<Double> s = new Stack<Double>();
		String token = expression;
		Double a, b ,result = 0.0;
		boolean isNumber = false;
		int i = 0;
		while(i<token.length() &&token.charAt(i)!='='){
			try{
				isNumber = true;
				result = Double.parseDouble(token.charAt(i)+"");
			}catch(Exception e){
				isNumber = false;
			}
			if(isNumber){
				s.push(result);
			}else{
				switch(token.charAt(i)){
				case '+':a = s.pop();b = s.pop();
				s.push((a+b));break;
				case '-':a = s.pop();b = s.pop();
				s.push((a-b));break;
				case '*':a = s.pop();b = s.pop();
				s.push((a*b));break;
				case '/':a = s.pop();b = s.pop();
				s.push((a/b));break;
				case '^':a = s.pop();b = s.pop(); //b的a次幂
				s.push(Math.exp(a*Math.log(b)));break;
				}
			}
			i++;
		}
		return s.peek();
	}
	
	/**
	 * 将中缀表达式转化为后缀表达式，能够判断两位以上的整数
	 * 
	 */
	public static String infixtoPostfix2(String expression) {
		Stack<Character> s = new Stack<Character>();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		int j = 0;
		char ch;
		while (i < expression.length()) {
			char token = expression.charAt(i);
			if (token >= '0' && token <= '9') {
				j = i;
				while(j<expression.length()&&expression.charAt(j)>='0'&& expression.charAt(j)<='9'){
					token = expression.charAt(j);
					sb.append(token);
					System.out.print(token);
					j = j+1;
				}
				i = j-1;
				sb.append(" ");
				System.out.print(" ");
			} else {
				switch (token) {
				case ')':
					while (!s.isEmpty() && s.peek() != '(') {
						ch = s.pop();
						sb.append(ch+" ");
						System.out.print(ch + " ");
					}
					s.pop();
					break;
				case '(':
					s.push(token);
					break;
				case '^':
					while (!s.isEmpty() && !(s.peek() == '^' || s.peek() == '(')) {
						ch = s.pop();
						sb.append(ch+" ");
						System.out.print(ch+" ");
					}
					s.push(token);
					break;
				case '*':
				case '/':
					while(!s.isEmpty()&& s.peek()!='(' && s.peek()!='+'&& s.peek()!='-' ){
						ch = s.pop();
						sb.append(ch+" ");
						System.out.print(ch+" ");
					}
					s.push(token);
					break;
				case '+':
				case '-':
					while (!s.isEmpty() && s.peek() != '(') {
						ch = s.pop();
						sb.append(ch+" ");
						System.out.print(ch+" ");
					}
					s.push(token);
					break;
				}
			}
			i++;
		}
		while(!s.isEmpty()){
			ch = s.pop();
			sb.append(ch+" ");
			System.out.print(ch+" ");
		}
		System.out.println();
		return sb.toString();
	}
	
	/**
	 * 将中缀表达式转化为后缀表达式
	 */
	public static void infixtoPostfix(String expression) {
		Stack<Character> s = new Stack<Character>();
		int i = 0;
		Character token;
		while ((token=expression.charAt(i))!='=') {
			if (token >= '0' && token <= '9') {
				System.out.print(token + " ");
			} else {
				switch (token) {
				case ')':
					while (!s.isEmpty() && s.peek() != '(') {
						System.out.print(s.pop() + " ");
					}
					s.pop();
					break;
				case '(':
					s.push(token);
					break;
				case '^':
					while (!s.isEmpty() && !(s.peek() == '^' || s.peek() == '(')) {
						System.out.print(s.pop());
					}
					s.push(token);
					break;
				case '*':
				case '/':
					while(!s.isEmpty()&& s.peek()!='(' && s.peek()!='+'&& s.peek()!='-' ){
						System.out.print(s.pop()+" ");
					}
					s.push(token);
					break;
				case '+':
				case '-':
					while (!s.isEmpty() && s.peek() != '(') {
						System.out.print(s.pop() + " ");
					}
					s.push(token);
					break;
				}
			}
			i++;
		}
		while(!s.isEmpty()){
			System.out.print(s.pop());
		}
		System.out.println();
	}
	/**
	 * 约瑟夫问题
	 * @param m 每隔m次去掉一个
	 * @param n编号1-n
	 */
	public static void pass(int m, int n) {
		int i, j, mPrime, numLeft;
		ArrayList<Integer> L = new ArrayList<Integer>();
		for (i = 1; i <= n; i++)
			L.add(i);
		ListIterator<Integer> iter = L.listIterator();
		Integer item = 0;
		numLeft = n;
		mPrime = m % n;
		for (i = 0; i < n; i++) {
			mPrime = m % numLeft;
			if (mPrime <= numLeft / 2) {
				if (iter.hasNext())
					item = iter.next();
				for (j = 0; j < mPrime; j++) {
					if (!iter.hasNext())
						iter = L.listIterator();
					item = iter.next();
				}
			} else {
				for (j = 0; j < numLeft - mPrime; j++) {
					if (!iter.hasPrevious())
						iter = L.listIterator(L.size());
					item = iter.previous();
				}
			}
			System.out.print("Removed" + item + "");
			iter.remove();
			if (!iter.hasNext())
				iter = L.listIterator();
			System.out.println();
			for (Integer x : L)
				System.out.print(x + "");
			System.out.println();
			numLeft--;
		}
		System.out.println();
	}

	
	public static <E extends Comparable<? super E>> E findMax(E[] array){
		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if((array[i].compareTo(array[maxIndex]))> 0)
				maxIndex = i;
		}	
		return array[maxIndex];
	}

}

class Shape implements Comparable<Shape>{
	
	private int radius;
	
	public Shape(int radius){
		this.radius = radius;
	}
	
	@Override
	public int compareTo(Shape o) {
		
		return (this.radius - o.radius);
	}
	
	public String toString(){
		return String.valueOf(this.radius);
	}
	
}
/**
 *如果Square不重写Comparable, 对Square 对象进行比较时，
 *会调用父类的比较方法，也就是比较该对象包含的父类属性
 * @author zhangqd
 */
class Square extends Shape  {
	
	private int width;
	
	public Square(int radius, int width) {
		super(radius);
		this.width = width;
	}
	
	public int compareTo(Shape s){
		Square sq = (Square) s;
		return this.width - sq.width;
	}
	
	public String toString(){
		return String.valueOf(width);
	}
	
}

