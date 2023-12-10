package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

public class Affine implements Transformation {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double f;
    private final double e;

    public Affine(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = a * x + b * y + c;
        double newY = d * x + e * y + f;

        return new Point(newX, newY);
    }
}
