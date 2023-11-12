package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double number) implements Expr {
        public double evaluate() {
            return number;
        }
    }

    record Negate(Expr number) implements Expr {
        public double evaluate() {
            return -number().evaluate();
        }
    }

    record Exponent(Expr number, double exp) implements Expr {
        public double evaluate() {
            return Math.pow(number().evaluate(), exp);
        }
    }

    record Addition(Expr number1, Expr number2) implements Expr {
        public double evaluate() {
            return number1.evaluate() + number2.evaluate();
        }
    }

    record Multiplication(Expr number1, Expr number2) implements Expr {
        public double evaluate() {
            return number1.evaluate() * number2().evaluate();
        }
    }
}
