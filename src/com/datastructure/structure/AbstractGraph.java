package com.datastructure.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract  class AbstractGraph<V> implements Graph<V> {
	/**
	 * 图的顶点列表信息
	 */
	protected List<V> vertices;
	/**
	 * 图中每个顶点的邻居,邻接线性表*/
	protected List<List<Integer>> neighbors;
	
	/**
	 * 使用数组中指定的边喝顶点创建图
	 * @param edges
	 * @param vertices
	 */
	protected  AbstractGraph(int[][] edges, V[] vertices){
		this.vertices = new ArrayList<V>();
		for(int i = 0; i < vertices.length; i++)
			this.vertices.add(vertices[i]);
		createAdjacencyLists(edges, vertices.length);
	}
	
	/**
	 * 使用存储在线性表中的指定边和顶点创建图
	 * @param edges
	 * @param vertices
	 */
	protected  AbstractGraph(List<Edge> edges, List<V> vertices){
		this.vertices = vertices;
		createAdjacencyLists(edges, vertices.size());
	}
	
	/**
	 * 使用数组中指定的边喝整数顶点1,2...创建图
	 * Construct a graph for integer vertices 0,1, and edge array
	 * @param edges
	 * @param numberOfVertices
	 */
	protected  AbstractGraph(int[][] edges, int numberOfVertices){
		vertices = new ArrayList<V>();
		for(int i = 0; i < numberOfVertices; i++)
			vertices.add((V)new Integer(i));
		createAdjacencyLists(edges, numberOfVertices);
	}
	
	/**
	 * 使用线性表中指定的边喝整数顶点1，2...来创建图
	 * Construct a graph for integer vertices 0,1,..and edge list
	 * @param edges
	 * @param numberOfVertices
	 */
	protected  AbstractGraph(List<Edge> edges,int numberOfVertices){
		vertices = new ArrayList<V>();
		for(int i = 0; i < numberOfVertices; i++)
			vertices.add((V)new Integer(i));
		createAdjacencyLists(edges, numberOfVertices);
	}
	
	/**
	 * Construct adjacency lists for each vertex
	 * @param edges
	 * @param numberOfVerices
	 */
	private void createAdjacencyLists(int[][] edges, int numberOfVertices){
		neighbors = new ArrayList<List<Integer>>();
		for(int i = 0; i < numberOfVertices; i++)
			neighbors.add(new ArrayList<Integer>());
		for(int i = 0; i < numberOfVertices; i++){
			int u = edges[i][0];  //获得各个边
			int v = edges[i][1];
			neighbors.get(u).add(v);
		}
	}
	
	private void createAdjacencyLists(List<Edge> edges, int numberOfVertices){
		//create a linked list
		neighbors = new ArrayList<List<Integer>>();
		for(int i = 0; i < numberOfVertices; i++)
			neighbors.add(new ArrayList<Integer>());
		for (Edge edge : edges) {
			neighbors.get(edge.u).add(edge.v);
		}
	}
	/**
	 * 返回图中顶点的个数
	 * @return
	 */
	@Override
	public int getSize() {
		return vertices.size();
	}
	
	/**
	 * 返回途中的顶点
	 * @return
	 */
	public List<V> getVertices() {
		return vertices ;
	}

	/**
	 * Return the object for the specified vertex
	 * @param index
	 * @return
	 */
	public V getVertex(int index) {
		return vertices.get(index);
	}

	/**
	 * Return the index for the specified vertex
	 * @param vertex
	 * @return
	 */
	public int getIndex(V vertex) {
		return vertices.indexOf(vertex);
	}
	
	
	public List<Integer> getNeighbors(int index) {
		return neighbors.get(index);
	}

	/**
	 * Return the degree for a specified vertex
	 * @param index
	 * @return
	 */
	public int getDegree(int index) {
		return neighbors.get(index).size();
	}

	/**
	 * Return the adjacency matrix
	 * @return
	 */
	public int[][] getAdjacencyMatrix() {
		int[][] adjacencyMatrix = new int[getSize()][getSize()];
		for(int i = 0; i < neighbors.size();i++)
			for(int j = 0; j < neighbors.get(i).size(); j++){
				int v = neighbors.get(i).get(j);
				adjacencyMatrix[i][v] = 1;
			}
		return adjacencyMatrix;
	}

	/**
	 * Print the adjacency matrix
	 */
	public void printAdjacencyMatrix() {
		int[][] adjacencyMatrix = getAdjacencyMatrix();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				System.out.print(adjacencyMatrix[i][j]+" ");
			}
		}
		System.out.println();
	}

	/**
	 * Print the edges
	 */
	public void printEdges() {
		for(int u = 0; u < neighbors.size(); u++){
			System.out.print("Vertex "+u+": ");
			for(int j = 0; j < neighbors.get(u).size(); j++){
				System.out.print("("+u+","+neighbors.get(u).get(j)+") ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * Obtain a dfs tree starting from vertex v
	 */
	public Tree dfs(int vertex) {
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[vertices.size()];
		for(int i = 0; i < parent.length; i++)
			parent[i] = -1;  //Initialize 
		//Mark visited vertices
		boolean[] isVisited = new boolean[vertices.size()];
		//Recursively search
		dfs(vertex,parent,searchOrders,isVisited);
		
		return new Tree(vertex,parent,searchOrders);
	}
	
	private void dfs(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited){
		//Store the visited vertex
		searchOrders.add(v);
		isVisited[v] = true;
		for(int i : neighbors.get(v)){
			if(!isVisited[i]){
				parent[i] = v;
				dfs(i,parent,searchOrders,isVisited);
			}
		}
	}
	
	
	/**
	 * Starting bfs search from vertex v
	 */
	public Tree bfs(int v) {
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[vertices.size()];
		for(int i = 0; i < parent.length; i++){
			parent[i] = -1;
		}
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[vertices.size()];
		queue.offer(v);
		isVisited[v] = true;
		while(!queue.isEmpty()){
			int u = queue.poll();
			searchOrders.add(u);
			for(int w: neighbors.get(u)){
				if(!isVisited[w]){
					queue.offer(w);
					parent[w] = u; //The parent of w is u
					isVisited[w] = true;
				}
			}
		}
		return new Tree(v, parent, searchOrders);
	}
	
	/**
	 * Return a Hamiltonian path from the specified vertex object
	 * Return null if the graph does not contain a Hamiltonian path
	 */
	public List<Integer> getHamiltonianPath(V vertex){
		return getHamiltonianPath(getIndex(vertex));
	}
	
	public List<Integer> getHamiltonianPath(int v){
		int[] next = new int[getSize()];
		for(int i=0;i<next.length;i++){
			next[i] = -1;
		}
		boolean[] isVisited = new boolean[getSize()];
		List<Integer> result = null;
		for(int i=0;i<getSize();i++)
			reorderNeighhborsBasedOnDegree(neighbors.get(i));
		if(getHamiltonianPath(v,next,isVisited)){
			result = new ArrayList<Integer>();
			int vertex = v;
			while(vertex!=-1){
				result.add(vertex);
				vertex = next[vertex];
			}
		}
		return result;
	}
	
	/**
	 * Recorder the adjacency list in increasing order of degrees
	 * @param list
	 */
	private void reorderNeighhborsBasedOnDegree(List<Integer> list){
		for(int i=list.size()-1;i>=1;i--){
			//Find the maximum in the list[0..1]
			int currentMaxDegree = getDegree(list.get(0));
			int currentMaxIndex = 0;
			for(int j=1;j<=i;j++){
				if(currentMaxDegree<getDegree(list.get(j))){
					currentMaxDegree = getDegree(list.get(j));
					currentMaxIndex = j;
				}
			}
			//Swap list[i] with list[currentMaxIndex] if necessary
			if(currentMaxIndex!=i){
				int temp = list.get(currentMaxIndex);
				list.set(currentMaxIndex, list.get(i));
				list.set(i, temp);
			}
		}
	}
	
	/**
	 * REturn true if all elements in array isVisited are true
	 * @param isVisited
	 * @return
	 */
	private boolean allVisited(boolean[] isVisited){
		boolean result = true;
		for(int i=0;i<getSize();i++)
			result = result&&isVisited[i];
		return result;
	}
	
	/**
	 * Search for a Hamiltonian path from v
	 * @param v
	 * @param next
	 * @return
	 */
	private boolean getHamiltonianPath(int v, int[] next,boolean[] isVisited){
		isVisited[v] = true;
		if(allVisited(isVisited))
			return true;
		for(int i=0;i<neighbors.get(v).get(i);i++){
			int u = neighbors.get(v).get(i);
			if(!isVisited[u]&&getHamiltonianPath(u,next,isVisited)){
				next[v] = u;
				return true;
			}
		}
		isVisited[v] = false;
		return false;
	}
	
	
	public static class Edge{
		public int u;
		public int v;
		public Edge(int u, int v){
			this.u = u;
			this.v = v;
		}
	}
	
	

	/**
	 *Tree inner class inside the AbstractGraph class 
	 */
	public  class Tree{
		//The root of the tree
		private int root;
		//Store the parent of each vertex
		private int[] parent;
		//Store the search vertex
		private List<Integer> searchOrders;
		
		public Tree(int root, int[] parent, List<Integer> searchOrders){
			this.root = root;
			this.parent = parent;
			this.searchOrders = searchOrders;
		}
		
		public Tree(int root, int[] parent){
			this.root = root;
			this.parent = parent;
		}
		
		public int getRoot(){
			return root;
		}
		
		public int getParent(int v){
			return parent[v];
		}
		
		public List<Integer> getSearchOrders(){
			return searchOrders;
		}
		
		public int getNumberOfVerticesFount(){
			return searchOrders.size();
		}
		/**
		 * Return the path of vertices from a vertex index to the root
		 * @param index
		 * @return
		 */
		public List<V> getPath(int index){
			ArrayList<V> path = new ArrayList<V>();
			do{
				path.add(vertices.get(index));
				index = parent[index];
			}while(index!=-1);
			return path;
		}
		
		/**
		 * Print a path from the root to vertex v
		 * @param index
		 */
		public void printPath(int index){
			List<V> path = getPath(index);
			System.out.print("A path from "+vertices.get(root)+" to "+vertices.get(index)+": ");
			for(int i = path.size()-1;i>=0;i--)
				System.out.print(path.get(i)+" ");
		}
		
		/**
		 * Print the whole tree
		 */
		public void printTree(){
			System.out.println("Root is "+vertices.get(root));
			System.out.println("Edges: ");
			for(int i=0;i<parent.length;i++){
				if(parent[i]!=-1){
					System.out.print("("+vertices.get(parent[i])+","+vertices.get(i)+")");
				}
			}
			System.out.println();
		}

	}
	
	
}
