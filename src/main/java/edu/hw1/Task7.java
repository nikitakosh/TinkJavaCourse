package edu.hw1;

public class Task7 {
    private Task7() {
    }

    public static int rotateRight(int n, int shift) {
        String nStr = Integer.toBinaryString(n);
        char[] newStr = new char[nStr.length()];
        for (int i = 0; i < nStr.length(); i++) {
            newStr[(i + shift) % nStr.length()] = nStr.charAt(i);
        }
        int ans = 0;
        for (int i = 0; i < newStr.length; i++) {
            ans += Math.pow(2, newStr.length - 1 - i) * (newStr[i] - '0');
        }
        return ans;
    }

    public static int rotateLeft(int n, int shift) {
        String nStr = Integer.toBinaryString(n);
        char[] newStr = new char[nStr.length()];
        for (int i = 0; i < nStr.length(); i++) {
            newStr[Math.floorMod(i - shift, nStr.length())] = nStr.charAt(i);
        }
        int ans = 0;
        for (int i = 0; i < newStr.length; i++) {
            ans += Math.pow(2, newStr.length - 1 - i) * (newStr[i] - '0');
        }
        return ans;
    }
}
