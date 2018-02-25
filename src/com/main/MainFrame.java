package com.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * �Ԍm������
 * 
 * @author Administrator
 *
 */
public class MainFrame extends JFrame {

	private JButton findPathBtn = null;
	private JButton generateMazeBtn = null;
	private Maze maze = null;

	public MainFrame(int m, int n) {

		findPathBtn = new JButton("����·��");
		generateMazeBtn = new JButton("���������Թ�");
		JPanel north = new JPanel();
		north.add(findPathBtn);
		north.add(generateMazeBtn);
		maze = new Maze(m, n);
		this.add(maze, BorderLayout.CENTER);
		this.add(north, BorderLayout.SOUTH);
		this.addAction();

	}

	/**
	 * ע�����¼�
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
		m.setTitle("�Թ�");
		m.setSize(615, 700);
		m.setVisible(true);
		m.setResizable(false);
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
