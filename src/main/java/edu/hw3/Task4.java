package edu.hw3;

import java.util.LinkedHashMap;
import java.util.Map;

public class Task4 {
    private Task4() {
    }

    public static String convertToRoman(int number) {
        int numberCopy = number;
        final int ROMAN_M = 1000;
        final int ROMAN_CM = 900;
        final int ROMAN_D = 500;
        final int ROMAN_CD = 400;
        final int ROMAN_C = 100;
        final int ROMAN_XC = 90;
        final int ROMAN_L = 50;
        final int ROMAN_XL = 40;
        final int ROMAN_X = 10;
        final int ROMAN_IX = 9;
        final int ROMAN_V = 5;
        final int ROMAN_IV = 4;
        final int ROMAN_I = 1;
        final int MAX_ROMAN_NUM = 3999;
        if (numberCopy <= 0 || numberCopy > MAX_ROMAN_NUM) {
            throw new IllegalArgumentException("number must be greater than 0 and less than 3999");
        }
        Map<Integer, String> romanNumerals = new LinkedHashMap<>();
        romanNumerals.put(ROMAN_M, "M");
        romanNumerals.put(ROMAN_CM, "CM");
        romanNumerals.put(ROMAN_D, "D");
        romanNumerals.put(ROMAN_CD, "CD");
        romanNumerals.put(ROMAN_C, "C");
        romanNumerals.put(ROMAN_XC, "XC");
        romanNumerals.put(ROMAN_L, "L");
        romanNumerals.put(ROMAN_XL, "XL");
        romanNumerals.put(ROMAN_X, "X");
        romanNumerals.put(ROMAN_IX, "IX");
        romanNumerals.put(ROMAN_V, "V");
        romanNumerals.put(ROMAN_IV, "IV");
        romanNumerals.put(ROMAN_I, "I");
        StringBuilder romanNum = new StringBuilder();
        for (Map.Entry<Integer, String> entry : romanNumerals.entrySet()) {
            while (numberCopy >= entry.getKey()) {
                numberCopy -= entry.getKey();
                romanNum.append(entry.getValue());
            }
        }
        return romanNum.toString();
    }
}
