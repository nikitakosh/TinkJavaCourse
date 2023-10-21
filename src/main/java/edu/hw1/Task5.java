package edu.hw1;

import java.util.ArrayList;
import java.util.List;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int num) {
        StringBuilder numStr = new StringBuilder(String.valueOf(num));
        while (numStr.length() > 1) {
            List<Character> newNum = new ArrayList<>();
            if (numStr.length() % 2 == 0) {
                for (int i = 0; i < numStr.length() - 1; i += 2) {
                    newNum.add((char) (numStr.charAt(i) - '0' + numStr.charAt(i + 1) - '0'));
                }
            } else {
                for (int i = 0; i < numStr.length() - 2; i += 2) {
                    newNum.add((char) (numStr.charAt(i) - '0' + numStr.charAt(i + 1) - '0'));
                }
                newNum.add(numStr.charAt(numStr.length() - 1));
            }
            int l = 0;
            int r = numStr.length() - 1;
            boolean flag = true;
            while (l < r) {
                if (numStr.charAt(l) != numStr.charAt(r)) {
                    flag = false;
                    break;
                }
                l++;
                r--;
            }
            if (flag) {
                return true;
            }
            numStr = new StringBuilder();
            for (char digit : newNum) {
                numStr.append((int) digit);
            }
        }
        return false;
    }
}
