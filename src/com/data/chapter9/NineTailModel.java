package com.data.chapter9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.datastructure.structure.AbstractGraph;
import com.datastructure.structure.AbstractGraph.Edge;
import com.datastructure.structure.UnweightedGraph;

/**
 * Java语言进阶篇p179,
 * 九个硬币反面的问题
 * @author zhangqd
 *
 */
public class NineTailModel {
	
	public static final int NUMBER_OF_NODES = 512;
	
	protected AbstractGraph<Integer>.Tree tree;
	/**
	 * Construct a model
	 */
	public NineTailModel(){
		List<Edge> edges = getEdges();
		UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(edges, NUMBER_OF_NODES);
		//Obtain a bfs tree rooted at the target node
		tree = graph.bfs(511);
		
	}
	
	private List<Edge> getEdges(){
		List<Edge> edges = new ArrayList<Edge>();
		for(int u=0;u<NUMBER_OF_NODES;u++)
			for(int k=0;k<9;k++){
				char[] node = getNode(u);
				if(node[k]=='H'){
					int v = getFlippedNode(node,k);
				edges.add(new Edge(v,u));
				}
			}
		return edges;
	}
	
	/**
	 * 返回一个包含9个H和T字符的节点
	 * @param index
	 * @return
	 */
	public static char[] getNode(int index){
		char[] result = new char[9];
		for(int i=0;i<9;i++){
			int digit = index % 2;
			if(digit==0)
				result[8-i] = 'H';
			else
				result[8-i] = 'T';
			index = index / 2;
		}
		return result;
	}
	
	/**
	 * 返回指定字符顶点的下标
	 * @param node
	 * @return
	 */
	public static int getIndex(char[] node){
		int result = 0;
		for(int i=0;i<9;i++){
			if(node[i]=='T')
				result = result * 2+1;
			else
				result = result *2 +0;
		}
		return result;
	}
	
	/**
	 * 反转指定位置的节点并且返回被反转节点的下标
	 * @param node
	 * @param positon
	 * @return
	 */
	public static int getFlippedNode(char[] node, int position){
		int row = position / 3;
		int column = position % 3;
		flipACell(node, row, column);
		flipACell(node,row-1,column);
		flipACell(node,row+1,column);
		flipACell(node,row, column+1);
		flipACell(node,row,column-1);
		return getIndex(node);
	}
	/**
	 * 反转 指定行和列的顶点
	 * @param node
	 * @param row
	 * @param column
	 */
	public static void flipACell(char[] node, int row, int column){
		if(row >=0 && row <=2 && column >=0 && column <=2){
			if(node[row*3+column]=='H')
				node[row*3+column] = 'T';//Flip from H to T
			else
				node[row*3+column] = 'H';//Flip from T to H
		}
	}
	
	public List<Integer> getShortestPath(int nodeIndex){
		return tree.getPath(nodeIndex);
	}
	
	public static void printNode(char[] node){
		for(int i=0;i<9;i++)
			if(i%3!=2)
				System.out.print(node[i]);
			else
				System.out.println(node[i]);
		System.out.println();
	}
	public static void main(String[] args) {
		System.out.println("Enter an initial nine coin H's and T's");
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		char[] initialNode = s.toCharArray();
		NineTailModel model = new NineTailModel();
		List<Integer> path = model.getShortestPath(NineTailModel.getIndex(initialNode));
		for(int i=0;i<path.size();i++)
			NineTailModel.printNode(NineTailModel.getNode(path.get(i).intValue()));
	}
	
}
