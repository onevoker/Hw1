package edu.hw10.Task2.fibonacci;

import edu.hw10.Task2.cache.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
