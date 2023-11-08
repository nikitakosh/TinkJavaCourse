package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    private Task7() {}

    public static boolean regex1(String str) {
        Pattern pattern = Pattern.compile("^[01]{2,}0$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean regex2(String str) {
        Pattern pattern = Pattern.compile("^([01])[01]*([01])$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find() && matcher.group(1).equals(matcher.group(2));
    }

    public static boolean regex3(String str) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
