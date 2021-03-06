package com.javarush.task.task24.task2413;

import java.util.*;

public class Arkanoid {

    private int width, height;
    private Ball ball;
    private Stand stand;
    private List<Brick> bricks;

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public void setBall(Ball ball){
        this.ball=ball;
    }

    public Ball getBall() {
        return ball;
    }

    public void setStand(Stand stand){
        this.stand=stand;
    }

    public Stand getStand(){
        return stand;
    }

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public  static  void main(String[] args)
    {
        
    }
}
