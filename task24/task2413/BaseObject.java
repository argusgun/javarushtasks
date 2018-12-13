package com.javarush.task.task24.task2413;

abstract class BaseObject {

    protected double x,y,radius;

    public BaseObject(double a, double b, double c)
    {
        setX(a);
        setY(b);
        setRadius(c);
    }

    abstract void move();

    abstract void draw(Canvas canvas);

    public boolean isIntersec(BaseObject o)
    {
        return (((this.x-o.x)*(this.x-o.x)+(this.y-o.y)*(this.y-o.y))<= Math.max(this.radius*this.radius,o.radius*o.radius));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
