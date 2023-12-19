package edu.hw10.Task2.fibonacci;

public class Calculator implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number <= 1) {
            return number;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
