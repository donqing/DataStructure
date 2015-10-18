package com.data.chapter9;

import java.util.ArrayList;
import java.util.List;

import com.datastructure.structure.AbstractGraph.Edge;
import com.datastructure.structure.UnweightedGraph;

/**
 * 骑士旅行问题
 * KnightTourModel创建一个图来建模这个问题
 * 创建图的顶点和边
 * @author zhangqd
 *
 */
public class KnightTourModel {
	//Define a graph
	private UnweightedGraph<Integer> graph;
	
	public KnightTourModel(){
		ArrayList<Edge> edges = getEdges();
		graph = new UnweightedGraph<Integer>(edges,64);
	}
	
	/**
	 * Get a Hamiltonian path starting from vertex
	 * @param v
	 * @return
	 */
	public List<Integer> getHamiltonianPath(int v){
		return graph.getHamiltonianPath(v);
	}
	
	public static ArrayList<Edge> getEdges(){
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				int u = i*8 +j;
				if(i-1>=0&&j-2>=0){
					int v1 = (i-1)*8+j-2;
					edges.add(new Edge(u,v1));
				}
				if(i-2>=0&&j-1>=0){
					int v2 = (i-2)*8+j-1;
					edges.add(new Edge(u,v2));
				}
				if(i-2>=0&&j+1<=7){
					int v3 = (i-2)*8+j-1;
					edges.add(new Edge(u,v3));
				}
				if(i-1>=0&&j+2<=7){
					int v4 = (i-1)*8+j+2;
					edges.add(new Edge(u,v4));
				}
				if(i+1<=7&&j+2<=7){
					int v5 = (i+1)*8+j+2;
					edges.add(new Edge(u,v5));
				}
				if(i+2<=7&&j+1<=7){
					int v6 = (i+2)*8+j+1;
					edges.add(new Edge(u,v6));
				}
				if(i+2<=7&&j-1>=0){
					int v7 = (i+2)*8+j-1;
					edges.add(new Edge(u,v7));
				}
				if(i+1<=7&&j-2>=0){
					int v8 = (i+1)*8+j-2;
					edges.add(new Edge(u,v8));
				}
			}
		}
		return edges;
	}
	
}
