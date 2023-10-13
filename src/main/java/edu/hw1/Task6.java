package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task6 {
    private Task6() {
    }

    private static final int KAPREKAR_CONSTANT = 6174;
    private static final int DECIMAL_BASE = 10;

    public static int countK(int n) {
        if (n == KAPREKAR_CONSTANT) {
            return 0;
        }
        List<Integer> digits = new ArrayList<>();
        int tempN = n;
        while (tempN > 0) {
            digits.add(tempN % DECIMAL_BASE);
            tempN /= DECIMAL_BASE;
        }
        List<Integer> digitsAsc = new ArrayList<>(digits);
        Collections.sort(digitsAsc);
        List<Integer> digitsDesc = new ArrayList<>(digits);
        Collections.sort(digitsDesc);
        Collections.reverse(digitsDesc);
        int numAsc = 0;
        int numDesc = 0;
        for (int i = 0; i < digitsAsc.size(); i++) {
            numAsc += (int) Math.pow(DECIMAL_BASE, i) * digitsAsc.get(i);
        }
        for (int i = 0; i < digitsDesc.size(); i++) {
            numDesc += (int) Math.pow(DECIMAL_BASE, i) * digitsDesc.get(i);
        }
        return 1 + countK(numAsc - numDesc);
    }
}
