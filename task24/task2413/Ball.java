package com.javarush.task.task24.task2413;

public class Ball extends BaseObject {

    private double speed, direction, dx, dy;
    private  boolean isFrozen;

    public Ball(double a, double b, double c) {
        super(a, b, c);
    }

    public  Ball(double x, double y, double speed, double direction)
    {
        super(x, y,1);
        isFrozen=true;
        this.speed=speed;
        this.direction=direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void start()
    {
        isFrozen=false;
    }

    public void setDirection(double direction) {
        this.direction = direction;
        double angle = Math.toRadians(direction);
        dx = Math.cos(angle) * speed;
        dy = -Math.sin(angle) * speed;
        //checkRebound(1, Arkanoid.game.getWidth() - 1, 1, Arkanoid.game.getHeight() - 1);
    }

    public void checkRebound(int minx, int maxx, int miny, int maxy)
    {

    }

    @Override
    void move() {
        if(!isFrozen)
        {
            this.x+=dx;
            this.y+=dy;
        }
    }

    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(this.x,this.y,'O');

    }
}
