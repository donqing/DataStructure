package com.datastructure.structure;

import java.util.List;

/**
 * Graph
 * @author zhangqd
 *
 */
public interface Graph<V> {
	/**
	 * Return the number of vertex in this graph
	 * @return
	 */
	int getSize();
	/**
	 * Return the vertices in the graph
	 * @return
	 */
	List<V> getVertices();
	
	V getVertex(int index);
	
	int getIndex(V vertex);
	/**
	 * Return neighbors of the specified vertex
	 */
	List<Integer> getNeighbors(int index);
	
	/**
	 * Return the degree of the specified position index vertex
	 * @param index
	 * @return
	 */
	int getDegree(int index);
	
	/**
	 * 返回邻接矩阵
	 * @return
	 */
	int[][] getAdjacencyMatrix();
	
	void printAdjacencyMatrix();
	
	void printEdges();
	
	/**
	 * 获取一个深度优先搜索树
	 * @param index
	 * @return
	 */
	AbstractGraph<V>.Tree dfs(int index);
	
	/**
	 * 获取一个广度优先搜索树
	 * @param index
	 * @return
	 */
	AbstractGraph<V>.Tree bfs(int index);
	
	/*
	 * Return a Hamiltonian path from the specified vertex object
	 * Return null if the graph does not contain a Hamiltonian path
	 */
	List<Integer> getHamiltonianPath(V vertex);
	
	/**
	 * Return a Hamiltonian path from the specified vertex label
	 * Return null if the graph does not contain a Hamiltonian path
	 * @param index
	 * @return
	 */
	List<Integer> getHamiltonianPath(int index);
	
	
	
	
}
