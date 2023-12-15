package edu.hw11;

public class Fibonacci {


    public long fib(int number) {
        if (number == 0) {
            return 0;
        }
        if (number <= 2) {
            return 1;
        }
        return fib(number-1) + fib(number-2);
    }

}
