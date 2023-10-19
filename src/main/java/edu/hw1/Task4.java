package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String str) {
        char[] base = str.toCharArray();
        char[] ans = new char[base.length];
        if (base.length % 2 == 0) {
            for (int i = 0; i < base.length - 1; i += 2) {
                ans[i + 1] = base[i];
                ans[i] = base[i + 1];
            }
        } else {
            for (int i = 0; i < base.length - 2; i += 2) {
                ans[i + 1] = base[i];
                ans[i] = base[i + 1];
            }
            ans[base.length - 1] = base[base.length - 1];
        }
        return new String(ans);
    }
}
