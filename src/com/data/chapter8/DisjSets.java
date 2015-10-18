package com.data.chapter8;
/**
 * 不相交集合
 * @author zhangqd
 *
 */
public class DisjSets {
	
	private int[] s;
	/**
	 * Construct the disjoint sets object
	 * @param numElements
	 */
	public DisjSets(int numElements){
		s = new int[numElements];
		for(int i =0;i<s.length;i++)
			s[i] = -1;
	}
	/**
	 * Union two disjoint sets
	 * For simplicity,we assume root1 and root2 are distinct
	 * and represent set names;
	 * 按高度求秩
	 * @param root1 the root of set1
	 * @param root2 the root of set2
	 */
	public void union(int root1, int root2){
		if(s[root2]<s[root1])  //root2 is deeper
			s[root1] = s[root2];  //Make root2 new root
		else{
			if(s[root1]==s[root2])
				s[root1]--; //Update height if same
			s[root2] = s[root1];//Make root1 new root
		}
	}
	/**
	 * Perform a find.
	 * 路径压缩？对不相交集进行find
	 * @param x the element being searched for
	 * @return  the set containing x
	 */
	public int find(int x){
		if(s[x]<0)
			return x;
		else
			return s[x]= find(s[x]);
	}
}
