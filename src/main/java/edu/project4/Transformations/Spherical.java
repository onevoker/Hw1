package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

public class Spherical implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);

        return new Point(newX, newY);
    }
}
