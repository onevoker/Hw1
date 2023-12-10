package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

@SuppressWarnings("MagicNumber")
public class AnotherMyTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.pow(x, Math.log(y)) - y / x;
        double newY = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / 3;

        return new Point(newX, newY);
    }
}
