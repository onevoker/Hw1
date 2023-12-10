package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

public class Heart implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double sqr = Math.sqrt(x * x + y * y);
        double theta = sqr * Math.atan(y / x);

        double newX = sqr * Math.sin(theta);
        double newY = -(sqr * Math.cos(theta));

        return new Point(newX, newY);
    }
}
