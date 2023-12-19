package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

public class MyTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.sqrt((1 - y * y + x * Math.sin(y)));
        double newY = Math.sin(x) * Math.log(x + Math.pow(Math.E, y));

        return new Point(newX, newY);
    }
}
