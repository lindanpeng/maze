package com.main;

/**
 * ���鼯
 * 
 * @author Administrator
 *
 */
public class DisjSets {
	public int[] s;

	/**
	 * ��ʼ�����鼯
	 * 
	 * @param n    Ԫ�ظ���
	 */
	public DisjSets(int n) {
		s = new int[n];
		for (int i = 0; i < s.length; i++) {
			s[i] = -1;// ��ʼʱÿ��Ԫ�صĸ����±궼Ϊ-1
		}
	}

	/**
	 * �ϲ�����
	 * 
	 * @param root1
	 *            ��һ������
	 * @param root2
	 *            �ڶ�������
	 */
	public void union(int root1, int root2) {
		// ��ͨ�ϲ�
		/* s[root2]=root1;*/
		// ������ϲ�֮���߶Ⱥϲ�
		if (s[root2] < s[root1]) {
			s[root1] = root2;
		} else {
			if (s[root1] == s[root2])
				s[root1]--;// ???
			s[root2] = root1;
		}

	}
   /**
    * ���ҽڵ��������ϵ��㷨
    * @param x �ڵ�
    * @return  ���ϵĸ�
    */
	public int find(int x) {
		// ��ͨ�㷨
		/*
		 * if(s[x]<0) 
		 *      return x;
		 *  else 
		 *    return find(s[x]);
		 */
		// ·��ѹ���㷨
		if (s[x] < 0)
			return x;
		else
			return s[x] = find(s[x]);// �ҵ�ͬʱ˳��ѹ��·��

	}

}
