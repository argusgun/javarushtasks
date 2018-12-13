package com.javarush.task.task24.task2413;

public class Stand extends BaseObject {
    private double speed,direction;

    public Stand(double a, double b) {
        super(a, b, 3);
        this.speed=1;
        this.direction=0;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public  void moveLeft()
    {
        this.direction=-1;
    }

    public void moveRight()
    {
        this.direction=1;
    }

    @Override
    void move() {
        this.x+=getDirection()*getSpeed();
    }

    @Override
    void draw(Canvas canvas) {

    }


}
