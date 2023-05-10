package com.example.snake;
//this class rappresent the head of the Snake

public class SnakeHead {
    private int Head=3;
    private int X;
    private int Y;
    public void setX(int x){X=x;}
    public void setY(int y){Y=y;}
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public void setHead(int head){
        Head = head;
    }
    public int getHead(){
        return Head;
    }
    public int getTail(){
        return Tail;
    }

}
