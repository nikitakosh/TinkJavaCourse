package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    private Task5() {}

    public static boolean isValidLicensePlates(String licensePlates) {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{1,3}$");
        Matcher matcher = pattern.matcher(licensePlates);
        return matcher.find();
    }

}
