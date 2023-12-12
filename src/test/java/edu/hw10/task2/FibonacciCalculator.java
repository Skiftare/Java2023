package edu.hw10.task2;

public class FibonacciCalculator implements FibCalculator {
    @Override
    public long fib(int number) {
        return calculateFibonacci(number);
    }


    private long calculateFibonacci(int number) {
        if (number <= 1) {
            return number;
        } else {
            return fib(number - 1) + fib(number - 2);
        }
    }
}
