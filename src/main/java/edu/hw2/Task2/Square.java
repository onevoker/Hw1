package edu.hw2.Task2;

public class Square extends Rectangle {
    public Square() {
    }

    public Square(int side) {
        super(side, side);
    }

    public Rectangle setSide(int side) {
        return new Square(side);
    }
}
