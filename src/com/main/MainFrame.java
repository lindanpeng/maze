package com.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 迷宮主窗口
 * 
 * @author Administrator
 *
 */
public class MainFrame extends JFrame {

	private JButton findPathBtn = null;
	private JButton generateMazeBtn = null;
	private Maze maze = null;

	public MainFrame(int m, int n) {

		findPathBtn = new JButton("查找路径");
		generateMazeBtn = new JButton("重新生成迷宫");
		JPanel north = new JPanel();
		north.add(findPathBtn);
		north.add(generateMazeBtn);
		maze = new Maze(m, n);
		this.add(maze, BorderLayout.CENTER);
		this.add(north, BorderLayout.SOUTH);
		this.addAction();

	}

	/**
	 * 注册点击事件
	 */
	public void addAction() {
		findPathBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				maze.paintPath();
			}

		});
		generateMazeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				maze.repaint();
			}

		});
	}

	public static void main(String[] args) {

		MainFrame m = new MainFrame(30, 30);
		m.setTitle("迷宫");
		m.setSize(615, 700);
		m.setVisible(true);
		m.setResizable(false);
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
