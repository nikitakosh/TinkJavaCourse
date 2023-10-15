package edu.hw1;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        int mn1 = Integer.MAX_VALUE;
        int mx1 = Integer.MIN_VALUE;
        for (int num : a1) {
            mn1 = Math.min(mn1, num);
            mx1 = Math.max(mx1, num);
        }
        int mn2 = Integer.MAX_VALUE;
        int mx2 = Integer.MIN_VALUE;
        for (int num : a2) {
            mn2 = Math.min(mn2, num);
            mx2 = Math.max(mx2, num);
        }
        if (mn1 <= mn2) {
            return false;
        }
        return mx1 < mx2;
    }
}
