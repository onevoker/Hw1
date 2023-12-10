package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

public class Disk implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double coefficient = 1 / Math.PI;
        double theta = Math.PI * Math.sqrt(x * x + y * y);

        double newX = coefficient * Math.atan(y / x) * Math.sin(theta);
        double newY = coefficient * Math.atan(y / x) * Math.cos(theta);

        return new Point(newX, newY);
    }
}
