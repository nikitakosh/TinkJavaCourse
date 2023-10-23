package edu.hw3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Task4 {

    public static String convertToRoman(int num) {
        if (num <= 0 || num > 3999) {
            throw new IllegalArgumentException("number must be greater than 0 and less than 3999");
        }
        Map<Integer, String> romanNumerals = new LinkedHashMap<>();
        romanNumerals.put(1000, "M");
        romanNumerals.put(900, "CM");
        romanNumerals.put(500, "D");
        romanNumerals.put(400, "CD");
        romanNumerals.put(100, "C");
        romanNumerals.put(90, "XC");
        romanNumerals.put(50, "L");
        romanNumerals.put(40, "XL");
        romanNumerals.put(10, "X");
        romanNumerals.put(9, "IX");
        romanNumerals.put(5, "V");
        romanNumerals.put(4, "IV");
        romanNumerals.put(1, "I");
        StringBuilder romanNum = new StringBuilder();
        for (Map.Entry<Integer, String> entry : romanNumerals.entrySet()){
            while (num >= entry.getKey()){
                num -= entry.getKey();
                romanNum.append(entry.getValue());
            }
        }
        return romanNum.toString();
    }
}
