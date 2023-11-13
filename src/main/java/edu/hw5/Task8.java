package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    private Task8() {
    }

    public static boolean regex1(String str) {
        Pattern pattern = Pattern.compile("^([01]{2})*[01]$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean regex2(String str) {
        Pattern pattern = Pattern.compile("^0([01]{2})*$|^1([01]{2})*[01]$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean regex3(String str) {
        Pattern pattern = Pattern.compile("^(1*01*01*01*)*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean regex4(String str) {
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
