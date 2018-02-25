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
 * 生成迷宫的类
 * 
 * @author Administrator
 *
 */
public class Maze extends JPanel {
	// 迷宫尺寸m*n
	private int m;
	private int n;
	// 迷宫房间尺寸(正方形)
	private final int rSize = 10;
	// 迷宫宽度
	private int width;
	// 迷宫高度
	private int height;
	// 保存迷宫的数据
	private int[][] maze = null;
	// 判断是否已经绘制路径
	private boolean isPaintPath = false;

	/**
	 * 初始化数据
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
	 * 绘制迷宫路径
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
	 * 绘制随机迷宫
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
				// 画房间
				g.fillRect(rSize * i, rSize * j, rSize, rSize);
				maze[i][j] = 1;
			}
		// 迷宫的出口和入口打开
		g.setColor(Color.white);
		// 不相交集定义
		DisjSets dSets = new DisjSets(m * n);
		Random random = new Random();
		// 房间0、mn-1没有连通
		while (dSets.find(0) != dSets.find(m * n - 1)) {
			// 随机生成一个房间号a 0<=a<=mn-1
			int a = random.nextInt(m * n);
			// a的相邻房间用list存放
			List<Integer> neighbor = new ArrayList<Integer>();
			// 分别判断a的上、右、下、左房间是否存在，若存在放入neighbor
			if (a - n >= 0)
				neighbor.add(a - n);
			if (a + 1 < ((int) (a / n) + 1) * n)
				neighbor.add(a + 1);
			if (a + n < m * n)
				neighbor.add(a + n);
			if (a - 1 >= ((int) (a / n)) * n)
				neighbor.add(a - 1);
			// 生成随机数index, 0<=index<=neighbor.size()-1
			int index = random.nextInt(neighbor.size());
			// b房间是a的相邻房间号，我们考察这两个房间是否连通
			int b = neighbor.get(index);
			// a、b是否连通
			if (dSets.find(a) != dSets.find(b)) {
				// a、b不连通，union它们的集合
				int seta = dSets.find(a);
				int setb = dSets.find(b);
				dSets.union(seta, setb);
				int s = Math.min(a, b);
				// 计算"墙"的坐标
				int x, y;
				// 两房间编号差是1，隔开它们的是竖墙
				if (Math.abs(a - b) == 1) {
					x = (s % n) * 2 + 1 + 1;
					y = 2 * (int) (s / n) + 1;

				} else {
					x = 2 * (s % n) + 1;

					y = 2 * (int) (s / n) + 1 + 1;
				}
				// 拆墙，实际上是用白线把墙抹掉
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
