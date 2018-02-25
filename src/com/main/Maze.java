package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

/**
 * �����Թ�����
 * 
 * @author Administrator
 *
 */
public class Maze extends JPanel {
	// �Թ��ߴ�m*n
	private int m;
	private int n;
	// �Թ�����ߴ�(������)
	private final int rSize = 10;
	// �Թ����
	private int width;
	// �Թ��߶�
	private int height;
	// �����Թ�������
	private int[][] maze = null;
	// �ж��Ƿ��Ѿ�����·��
	private boolean isPaintPath = false;

	/**
	 * ��ʼ������
	 * 
	 * @param m
	 * @param n
	 */
	public Maze(int m, int n) {
		// add(panel, BorderLayout.CENTER);
		this.m = m;
		this.n = n;
		width = (n + n + 1) * rSize;
		height = (2 * m + 1) * rSize;
		maze = new int[2 * m + 1][2 * n + 1];
	}

	/**
	 * �����Թ�·��
	 */
	public void paintPath() {
		if (isPaintPath)
			return;
		isPaintPath = true;
		Graphics g = this.getGraphics();
		Point start = new Point(1, 1);
		Point end = new Point(2 * m - 1, 2 * n - 1);
		MazeRoad road = new MazeRoad(maze, start, end);
		maze=road.getPath();
		g.setColor(Color.red);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == 2) {
					g.fillRect(rSize * j, rSize * i, rSize, rSize);
				}
			}
		}
	}

	/**
	 * ��������Թ�
	 * 
	 * @param g
	 */
	public void paintMaze(Graphics g) {
		for (int i = 0; i < maze.length; i++) {
			Arrays.fill(maze[i], 0);
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		for (int i = 1; i < 2 * n + 1; i += 2)
			for (int j = 1; j < 2 * m + 1; j += 2) {
				// ������
				g.fillRect(rSize * i, rSize * j, rSize, rSize);
				maze[i][j] = 1;
			}
		// �Թ��ĳ��ں���ڴ�
		g.setColor(Color.white);
		// ���ཻ������
		DisjSets dSets = new DisjSets(m * n);
		Random random = new Random();
		// ����0��mn-1û����ͨ
		while (dSets.find(0) != dSets.find(m * n - 1)) {
			// �������һ�������a 0<=a<=mn-1
			int a = random.nextInt(m * n);
			// a�����ڷ�����list���
			List<Integer> neighbor = new ArrayList<Integer>();
			// �ֱ��ж�a���ϡ��ҡ��¡��󷿼��Ƿ���ڣ������ڷ���neighbor
			if (a - n >= 0)
				neighbor.add(a - n);
			if (a + 1 < ((int) (a / n) + 1) * n)
				neighbor.add(a + 1);
			if (a + n < m * n)
				neighbor.add(a + n);
			if (a - 1 >= ((int) (a / n)) * n)
				neighbor.add(a - 1);
			// ���������index, 0<=index<=neighbor.size()-1
			int index = random.nextInt(neighbor.size());
			// b������a�����ڷ���ţ����ǿ��������������Ƿ���ͨ
			int b = neighbor.get(index);
			// a��b�Ƿ���ͨ
			if (dSets.find(a) != dSets.find(b)) {
				// a��b����ͨ��union���ǵļ���
				int seta = dSets.find(a);
				int setb = dSets.find(b);
				dSets.union(seta, setb);
				int s = Math.min(a, b);
				// ����"ǽ"������
				int x, y;
				// �������Ų���1���������ǵ�����ǽ
				if (Math.abs(a - b) == 1) {
					x = (s % n) * 2 + 1 + 1;
					y = 2 * (int) (s / n) + 1;

				} else {
					x = 2 * (s % n) + 1;

					y = 2 * (int) (s / n) + 1 + 1;
				}
				// ��ǽ��ʵ�������ð��߰�ǽĨ��
				g.setColor(Color.white);
				maze[y][x] = 1;
				g.fillRect((x) * rSize, (y) * rSize, rSize, rSize);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		this.paintMaze(g);
		this.isPaintPath = false;
	}

}
