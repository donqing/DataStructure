package com.datastructure.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 加权图的，邻接线性表用优先队列实现
 * @author zhangqd
 *
 * @param <V>
 */
public class WeightedGraph<V> extends AbstractGraph<V> {
	//Priority adjacency lists
	private List<PriorityQueue<WeightedEdge>> queues;
	
	/**
	 * Construct a WeightedGraph from edges and vertices in arrays
	 * @param edges
	 * @param vertices
	 */
	public WeightedGraph(int[][] edges,V[] vertices){
		super(edges,vertices);
		createQueues(edges,vertices.length);
	}
	
	public WeightedGraph(int[][] edges, int numberOfVertices){
		super(edges,numberOfVertices);
		createQueues(edges,numberOfVertices);
	}
	
	public WeightedGraph(List<WeightedEdge> edges, List<V> vertices){
		super((List)edges,vertices);
		createQueues(edges,vertices.size());
	}
	
	public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices){
		super((List)edges,numberOfVertices);
		createQueues(edges,numberOfVertices);
	}
	
	
	/**
	 * Create priority adjacency lists from edge arrays
	 * @param edges
	 * @param numberOfVertices
	 */
	private void createQueues(int[][] edges, int numberOfVertices){
		queues = new ArrayList<PriorityQueue<WeightedEdge>>();
		for(int i=0;i<numberOfVertices;i++){
			queues.add(new PriorityQueue<WeightedEdge>());
		}
		for(int i = 0; i < edges.length; i++){
			int u = edges[i][0];
			int v = edges[i][1];
			int weight = edges[i][2];
			queues.get(u).offer(new WeightedEdge(u,v,weight));
		}
		
	}
	
	/**
	 * Create priority adjacency lists from edge lists
	 * @param edges
	 * @param numberOfVertices
	 */
	private void createQueues(List<WeightedEdge> edges, int numberOfVertices){
		queues = new ArrayList<PriorityQueue<WeightedEdge>>();
		for(int i = 0; i < numberOfVertices; i++){
			queues.add(new PriorityQueue<WeightedEdge>());
		}
		for(WeightedEdge edge : edges){
			queues.get(edge.u).offer(edge);//Insert an edge into the queue
		}
	}
	
	/**
	 * Display edges with weights
	 */
	public void printWeightedEdges(){
		for(int i = 0; i < queues.size(); i++){
			System.out.print("Vertex "+ i + ": ");
			for(WeightedEdge edge : queues.get(i)){
				System.out.print("("+edge.u+","+edge.v+","+edge.weight+")");
			}
			System.out.println();
		}
	}
	
	/**
	 * Get a minimum spanning tree rooted at vertex 0
	 * @return
	 */
	public MST getMinimumSpanningTree(){
		return getMinimumSpanningTree(0);
	}
	
	/**
	 * Get a minimum spanning tree rooted at a specified vertex
	 * @param startingIndex
	 * @return
	 */
	public MST getMinimumSpanningTree(int startingIndex){
		List<Integer> T = new ArrayList<Integer>();
		//T initially contains the startingVertex
		T.add(startingIndex);
		int numberOfVertices = vertices.size();
		int[] parent = new int[numberOfVertices];//Parent of a vertex
		//Initially set the parent of all vertices to -1
		for(int i = 0; i < parent.length; i++)
			parent[i] = -1;
		int totalWeight = 0;
		List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);
		//All vertices are found?
		while(T.size() < numberOfVertices){
			//Search for the vertex with the smallest edge adjacent to
			//a vertex in T
			int v = -1;
			double smallestWeight = Double.MAX_VALUE;
			for(int u : T){
				while(!queues.get(u).isEmpty()&&T.contains(queues.get(u).peek().v)){
					//Remove the edge from queues.get(u) if the adjacent
					//vertex of u is already in T
					queues.get(u).remove();
				}
				if(queues.get(u).isEmpty())
					continue;
				//Current smallest weight on an edge adjacent to u
				WeightedEdge edge = queues.get(u).peek();
				if(edge.weight < smallestWeight){
					v = edge.v;
					smallestWeight = edge.weight;
					//If v is added to the tree, u will be its parent
					parent[v] = u;
				}
			}//End of for
			T.add(v);//Add a new vertex to the tree
			totalWeight += smallestWeight;
		}
		return new MST(startingIndex,parent,T,totalWeight);
	}
	
	private List<PriorityQueue<WeightedEdge>> deepClone(List<PriorityQueue<WeightedEdge>> queues){
		List<PriorityQueue<WeightedEdge>> copiedQueues = new ArrayList<PriorityQueue<WeightedEdge>>();
		for(int i = 0; i < queues.size(); i++){
			copiedQueues.add(new PriorityQueue<WeightedEdge>());
			for(WeightedEdge edge : queues.get(i))
				copiedQueues.get(i).add(edge);
		}
		return copiedQueues;
	}
	
	/**
	 *MST is an inner class in WeightedGraph 
	 */
	public class MST extends Tree{
		//Total weight of all edges in the tree
		private int totalWeight;
		
		public MST(int root, int[] parent,List<Integer> searchOrder,int totalWeight) {
			super(root, parent,searchOrder);
			this.totalWeight = totalWeight;
		}
		
		public int getTotalWeight(){
			return totalWeight;
		}
		
	}
	
	/**
	 * Find single-source shortest paths
	 * @param sourceIndex
	 * @return
	 */
	public ShortestPathTree getShortestPath(int sourceIndex){
		//T stores the vertices whose path found so far
		List<Integer> T = new ArrayList<Integer>();
		//T initially contains the sourceVertex
		T.add(sourceIndex);
		//vertices is defined in AbstractGraph
		int numberOfVertices = vertices.size();
		//parent[v] stores the previous vertex of v in the path
		int[] parent = new int[numberOfVertices];
		//The parent of source is set to -1
		parent[sourceIndex] = -1;
		//costs[v] stores the cost of the path from v to the source 
		int[] costs = new int[numberOfVertices];
		for(int i = 0; i < costs.length; i++)
			costs[i] = Integer.MAX_VALUE;//Initial cost set to the infinity
		costs[sourceIndex] = 0;//Cost of source is 0
		//Get a copy of queues
		List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);
		//Expand verticesFound
		while(T.size() < numberOfVertices){
			int v = -1;//
			int smallestCost = Integer.MAX_VALUE;
			for(int u: T){
				while(!queues.get(u).isEmpty()&&T.contains(queues.get(u).peek().v)){
					queues.get(u).remove();
				}
				if(queues.get(u).isEmpty())
					continue;//All vertices adjacent to u are in verticesFound
				WeightedEdge e = queues.get(u).peek();
				if(costs[u]+e.weight < smallestCost){
					v = e.v;
					smallestCost = costs[u]+e.weight;
					//If v is added to the tree ,u will be its parent
					parent[v] = u;
				}
			}
			T.add(v);
			costs[v] = smallestCost;
		}
		return new ShortestPathTree(sourceIndex,parent,T,costs);
		
	}
	
	public class ShortestPathTree extends Tree{
		private int[] costs; //costs[v] is the cost from v to source(root)
		/**
		 * Construct a path
		 * @param source
		 * @param parent
		 * @param searchOrder
		 * @param costs
		 */
		public ShortestPathTree(int source, int[] parent,List<Integer> searchOrder,int[] costs){
			super(source,parent,searchOrder);
			this.costs = costs;
		}
		
		/**
		 * Return the cost for a path from the root to vertex v
		 * @param v
		 * @return
		 */
		public int getCost(int v){
			return costs[v];
		}
		
		/**
		 * Print paths from all vertices to the source
		 */
		public void printAllPaths(){
			System.out.println("All shortest paths from"+vertices.get(getRoot())+" are: ");
			for(int i = 0; i < costs.length; i++){
				printPath(i);//Print a path from i to the source
				System.out.println("(cost: "+costs[i]+")");
			}
		}
		
	}
	
	public List<PriorityQueue<WeightedEdge>> getWeightedEdges(){
		return queues;
	}
	
	public class WeightedEdge extends Edge implements Comparable<WeightedEdge>{
		
		public int weight;
		
		public WeightedEdge(int u, int v,int weight) {
			super(u, v);
			this.weight = weight;
		}

		@Override
		public int compareTo(WeightedEdge edge) {
			if(weight>edge.weight)
				return 1;
			else if(weight < edge.weight)
				return -1;
			else
				return 0;
		}
		
	}
}

