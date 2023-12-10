package edu.project4.Transformations;

import edu.project4.BaseClasses.Point;

@FunctionalInterface
public interface Transformation {
    Point apply(Point point);
}


