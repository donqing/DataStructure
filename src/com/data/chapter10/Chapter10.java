package com.data.chapter10;

import java.util.Random;


public class Chapter10 {
	public static void main(String[] args) {
		Node node1 = new Node(4);
		Node node2 = new Node(2);
		Node node3 = new Node(6);
		node1.left = node2;
		node1.right = node3;
		System.out.println(findMin(node1));
		System.out.println(findMax(node1));
		System.out.println(contains(2,node1));
		System.out.println(contains2(5,node1));
	}
	
	public static int findMin(Node root){
		if(root==null)
			return 0;
		if(root.left==null)
			return root.val;
		return findMin(root.left);
	}
	
	public static Node findMax(Node root){
		if(root == null)
			return null;
		else if(root.right == null)
			return root;
		return findMax(root.right);
	}
	
	public static boolean contains(int val, Node root){
		if(root == null)
			return false;  
		if(val<root.val)
			return contains(val,root.left);
		else if(val > root.val)
			return contains(val,root.right);
		else
			return true;
	}
	
	public static boolean contains2(int e, Node root){
		if(root == null)
			return false;
		Node tmp = root;
		boolean find = false;
		while(find == false && tmp!=null){
			if(e < tmp.val)
				tmp = tmp.left;
			else if(e > tmp.val)
				tmp = tmp.right;
			else
			find = true;
		}
		return find;
	}
	
}

class Node{
	int val;
	Node left;
	Node right;
	public Node(int val){
		this.val = val;
	}
	public String toString(){
		return val+"";
	}
}
