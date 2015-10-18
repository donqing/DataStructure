package com.data.chapter8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Chapter8 {
	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		node1.left = node2;
		node1.right = node3;
		node3.left = node4;
		node2.right = node5;
		System.out.println(countNodes(node1));
		System.out.println(depth(node1));
	}
	/**
	 * 计算二叉树中结点个数
	 * @param root
	 * @return
	 */
	public static int countNodes(Node root){
		if(root ==null)
			return 0;
		return 1+countNodes(root.left)+countNodes(root.right);
	}
	
	public static int depth(Node root){
		if(root == null)
			return -1;  //0是有特殊含义的，当没有左右根结点时，返回0
		if(root.left == null && root.right == null) return 0;
		int leftDepth = depth(root.left);
		int rightDepth = depth(root.right);
		int depth = 1+(leftDepth > rightDepth ? leftDepth:rightDepth);
		return depth;
	}
}

class Node{
	Node left;
	Node right;
	int val;
	public Node(int val){
		this.val = val;
	}
	public String toString(){
		return val+"";
	}
	
}