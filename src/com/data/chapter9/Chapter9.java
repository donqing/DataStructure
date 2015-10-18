package com.data.chapter9;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Graph 
 * @author zhangqd
 *
 */
public class Chapter9 {
	
private String str;
	
	
	private boolean[] visited;
	
	private int[][] matrix;
	
	private int number = 4;
	
	private String[] vertexs={"a","b","c","d"};
	
	private int[][] edges ={{0,1,1,1},{1,0,1,1},
			{1,1,0,1},{1,1,1,0}
	};
	
	
	private void buildGraph(String str){
		int length = str.length();
		matrix = new int[length][length];
		for(int i=0;i<length;i++)
			for(int j=0;j<length;j++){
				if(i==j)
					matrix[i][j] = 0;
				matrix[i][j] = 1;
			}
	}
	/**
	 * 图的深度遍历
	 */
	public void dfsTraverse(){
		visited = new boolean[number];
		for(int i = 0; i < number; i++){
			if(visited[i]==false)
				dfs(i);
		}
		System.out.println();
	}
	
	public void dfs(int i){
		visited[i] = true;
		System.out.print(vertexs[i]+" ");
		for(int j=0;j<number;j++){
			if(visited[j]==false && edges[i][j]==1)
				dfs(j);
		}
	}
	private Set<String> set = new HashSet<String>();
	public void bfsAllPath(){
		Queue<Integer> queue = new LinkedList<Integer>();

		for(int i=0;i<number;i++){
			queue.add(i);
			for(int j=0;j<number;j++){
				
			}
		}
	}
	
	private void bfsAllPath(int index, Set<Integer> set){
		
	}
	/**
	 * 图的广度遍历
	 */
	public void bfs(){
		visited = new boolean[number];
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<number;i++){
			if(visited[i]==false){
				visited[i] = true;
				System.out.print(vertexs[i]+" ");
				queue.add(i);
				while(!queue.isEmpty()){
					int j = queue.poll();
					for(int k=0;k<number;k++){
						if(edges[j][k]==1&&visited[k]==false){
							visited[k] = true;
							System.out.print(vertexs[k]+" ");
							queue.add(k);
						}
					}
				}
			}
		}
	}
	
	
//	public void printAll(int i){
//		if(i<number){
//			for(int j=0;j<number;j++){
//				System.out.print(vertexs[i]);
//				if(j!=i){
//					System.out.print(vertexs[j]);
//				}
//			}
//			
//		}
//		System.out.println();
//		printAll(i++);
//		
//	}
	

	public static void main(String[] args) {
		Chapter9 graph = new Chapter9();
		graph.dfsTraverse();
		System.out.println("------");
		graph.bfs();
	}

}
