package com.main;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
/**
 * �Թ�·���㷨��
 * @author Administrator
 *
 */
public class MazeRoad {
//1��ʾͨ·��0��ʾǽ��2��ʾ�߹���·,3��ʾ��·
private   int maze[][];
//�Թ����
private   Point start;
//�Թ�����
private  Point  end;
  MazeRoad(int maze[][],Point start,Point end){
	
	this.maze=maze;
	this.start=start;
	this.end=end;
}
 /**
  * ������ȱ��������Թ�·���㷨
  */
private void findPathWithDFS(){
		DFS(start);
}
/**
 * ������ȱ��������Թ�·���㷨
 */
private void findPathWithBFS(){
	Point[][] path=BFS();
	Stack<Point> stack=new Stack<Point>();
	Point curPos=end;
	while(curPos!=start){
		stack.push(curPos);
		curPos=path[curPos.y][curPos.x];
	}
    stack.push(start);
	while(!stack.isEmpty()){
	curPos=stack.pop();
	markFoot(curPos);
	}
	
}
 /**
  * ��ջ�����Թ����㷨
  * @return
  */
private boolean findPathWithStack(){
	Stack<Position> stack=new Stack<>();
	Point curPos=start;
	Position pos;
	do{
		if(canPass(curPos)){
			markFoot(curPos);//�����㼣
			stack.push(new Position(curPos,1));
			if(curPos.equals(end))
				return true;
			curPos=nextPos(curPos,1);//����
		}
		else{
			if(!stack.empty()){
				pos=stack.pop();
				while(pos.getDir()==4&&!stack.empty()){
					markNo(pos.getPos());
					pos=stack.pop();
				}
				if(pos.getDir()<4){
					pos.setDir(pos.getDir()+1);//�޸�ջ��λ�õķ���
					stack.push(new Position(pos.getPos(),pos.getDir()));
					curPos=nextPos(pos.getPos(),pos.getDir());
				}
			}
		}
	}while(!stack.empty());
	return false;
}
/**
 * DFSʵ���㷨
 * @param curPos ��ǰ����λ��
 * @return
 */
private boolean DFS(Point curPos){
	
	markFoot(curPos);
	if(curPos.equals(end)){ 
		return true;
	}
	for(int i=1;i<=4;i++){
		Point nextPos=nextPos(curPos,i);
		if(canPass(nextPos))
		{
			if(DFS(nextPos))
				return true;
		}
	}
	markNo(curPos);
	return false;
	 
}
/**
 * BFSʵ���㷨
 * @return
 */
private Point[][] BFS(){
	Point[][] pre=new Point[maze.length][maze[0].length];
	Deque<Point> queue=new LinkedList<>();
	queue.push(start);
	while(!queue.isEmpty())
	{
		Point pos=queue.pop();
		markNo(pos);
		if(pos.equals(end))
		{
			return pre;
		}
		for(int i=1;i<=4;i++)
		{
			Point nextPos=nextPos(pos,i);
			if(canPass(nextPos))
			{
				pre[nextPos.y][nextPos.x]=pos;
				queue.push(nextPos);
			}
		}
		
	}
	return pre;
}
/**
 * ��Ǹ�λ������·
 * @param pos
 */
private void markNo(Point pos) {
	maze[pos.y][pos.x]=3;
}
/**
 * ͨ����ǰλ���Լ����򣬵ó���һ��λ�õ�����
 * @param curPos ��ǰλ��
 * @param dir    ����
 * @return       ��һ��λ��
 */
private Point nextPos(Point curPos, int dir) {
	Point newPoint=new Point(curPos);
	switch(dir){
	case 1:newPoint.y+=1;break;
	case 2:newPoint.x+=1;break;
	case 3:newPoint.y-=1;break;
	case 4:newPoint.x-=1;break;
	}
	return newPoint;
}
/**
 * ��Ǹ�λ���Ѿ��߹�
 * @param curPos
 */
private void markFoot(Point curPos) {
	maze[curPos.y][curPos.x]=2;
	
}
/**
 * �жϸ�λ���Ƿ���ͨ��
 * @param curPos
 * @return
 */
private boolean canPass(Point curPos) {
	
	return maze[curPos.y][curPos.x]==1;
}
public int[][] getPath(){
	//this.findPathWithStack();
	//this.findPathWithBFS();
	this.findPathWithDFS();
	return maze;
}

}
/**
 * λ����Ϣ�࣬������ջ�����Թ�·�����㷨
 * @author Administrator
 *
 */
class Position {
//�Թ�λ��
private Point pos;
//����
private int dir;
public Position(){
	
}
public Position(Point pos,int dir){
	this.pos=new Point(pos.x,pos.y);
	this.dir=dir;
}

public Point getPos() {
	return pos;
}
public void setPos(Point pos) {
	this.pos = pos;
}
public int getDir() {
	return dir;
}
public void setDir(int dir) {
	this.dir = dir;
}

}

