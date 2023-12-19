package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

public class Sinusoidal implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.sin(x);
        double newY = Math.sin(y);

        return new Point(newX, newY);
    }
}
