package com.main;

/**
 * 并查集
 * 
 * @author Administrator
 *
 */
public class DisjSets {
	public int[] s;

	/**
	 * 初始化并查集
	 * 
	 * @param n    元素个数
	 */
	public DisjSets(int n) {
		s = new int[n];
		for (int i = 0; i < s.length; i++) {
			s[i] = -1;// 初始时每个元素的父亲下标都为-1
		}
	}

	/**
	 * 合并集合
	 * 
	 * @param root1
	 *            第一个集合
	 * @param root2
	 *            第二个集合
	 */
	public void union(int root1, int root2) {
		// 普通合并
		/* s[root2]=root1;*/
		// 灵巧求合并之按高度合并
		if (s[root2] < s[root1]) {
			s[root1] = root2;
		} else {
			if (s[root1] == s[root2])
				s[root1]--;// ???
			s[root2] = root1;
		}

	}
   /**
    * 查找节点所处集合的算法
    * @param x 节点
    * @return  集合的根
    */
	public int find(int x) {
		// 普通算法
		/*
		 * if(s[x]<0) 
		 *      return x;
		 *  else 
		 *    return find(s[x]);
		 */
		// 路径压缩算法
		if (s[x] < 0)
			return x;
		else
			return s[x] = find(s[x]);// 找的同时顺便压缩路径

	}

}
