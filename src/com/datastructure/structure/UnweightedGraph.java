package com.datastructure.structure;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {
	/**
	 * Construct a graph from edges and vertices stored in arrays
	 */
	public UnweightedGraph(int[][] edges, V[] vertices){
		super(edges, vertices);
	}
	
	public UnweightedGraph(List<Edge> edges, List<V> vertices){
		super(edges,vertices);
	}
	
	/**
	 * Construct a graph for integer vertices 0,1,...and edge list
	 * @param edges
	 * @param numberOfVertices
	 */
	public UnweightedGraph(List<Edge> edges, int numberOfVertices){
		super(edges,numberOfVertices);
	}
	
	/**
	 * Construct a graph from integer vertices 0,1..and edge array
	 * @param edges
	 * @param numberOfVertices
	 */
	public UnweightedGraph(int[][] edges, int numberOfVertices){
		super(edges, numberOfVertices);
	}
	
	
}
