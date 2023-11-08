package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {}

    public static boolean isSubstringInString(String str, String substr) {
        Pattern pattern = Pattern.compile(substr);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

}
