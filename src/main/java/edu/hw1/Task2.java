package edu.hw1;

public class Task2 {
    private Task2() {
    }

    private static final int BASE = 10;

    public static int countDigits(int num) {
        int count = num == 0 ? 1 : 0;
        int tempNum = num;
        while (tempNum > 0) {
            tempNum /= BASE;
            count++;
        }
        return count;
    }
}
