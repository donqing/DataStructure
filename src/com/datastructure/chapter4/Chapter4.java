package com.datastructure.chapter4;

import com.datastructure.structure.BinarySearchTree;

public class Chapter4 {
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		for (int i = 0; i < 6; i++) {
			tree.insert(i);
		}
		tree.printTree();
		System.out.println(tree.isBinarySearchTree());
	}

}



