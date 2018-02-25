package com.main;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
/**
 * 迷宫路径算法类
 * @author Administrator
 *
 */
public class MazeRoad {
//1表示通路，0表示墙，2表示走过的路,3表示死路
private   int maze[][];
//迷宫入口
private   Point start;
//迷宫出口
private  Point  end;
  MazeRoad(int maze[][],Point start,Point end){
	
	this.maze=maze;
	this.start=start;
	this.end=end;
}
 /**
  * 深度优先遍历查找迷宫路径算法
  */
private void findPathWithDFS(){
		DFS(start);
}
/**
 * 广度优先遍历查找迷宫路径算法
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
  * 用栈查找迷宫的算法
  * @return
  */
private boolean findPathWithStack(){
	Stack<Position> stack=new Stack<>();
	Point curPos=start;
	Position pos;
	do{
		if(canPass(curPos)){
			markFoot(curPos);//留下足迹
			stack.push(new Position(curPos,1));
			if(curPos.equals(end))
				return true;
			curPos=nextPos(curPos,1);//向下
		}
		else{
			if(!stack.empty()){
				pos=stack.pop();
				while(pos.getDir()==4&&!stack.empty()){
					markNo(pos.getPos());
					pos=stack.pop();
				}
				if(pos.getDir()<4){
					pos.setDir(pos.getDir()+1);//修改栈顶位置的方向
					stack.push(new Position(pos.getPos(),pos.getDir()));
					curPos=nextPos(pos.getPos(),pos.getDir());
				}
			}
		}
	}while(!stack.empty());
	return false;
}
/**
 * DFS实现算法
 * @param curPos 当前所处位置
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
 * BFS实现算法
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
 * 标记该位置是死路
 * @param pos
 */
private void markNo(Point pos) {
	maze[pos.y][pos.x]=3;
}
/**
 * 通过当前位置以及方向，得出下一个位置的坐标
 * @param curPos 当前位置
 * @param dir    方向
 * @return       下一个位置
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
 * 标记该位置已经走过
 * @param curPos
 */
private void markFoot(Point curPos) {
	maze[curPos.y][curPos.x]=2;
	
}
/**
 * 判断该位置是否能通过
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
 * 位置信息类，辅助用栈查找迷宫路径的算法
 * @author Administrator
 *
 */
class Position {
//迷宫位置
private Point pos;
//方向
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

