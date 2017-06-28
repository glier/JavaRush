package com.javarush.task.task24.task2413;

import java.awt.geom.Point2D;

/**
 * Created by Илья Борозденец on 28.06.2017.
 */
abstract class BaseObject {
    protected double x, y, radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public abstract void draw (Canvas canvas);

    public abstract void move ();

    public boolean isIntersec (BaseObject o) {
        if (Point2D.distance(x, y, o.getX(), o.getY()) <= Math.max(radius, o.getRadius()))
            return true;
        else return false;
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
