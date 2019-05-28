package controller.command.validation;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValid {


    public static boolean isEnglishValid(String text) {
        final String regex = "^[A-Z][a-z]{1,20}$";
       Pattern p = java.util.regex.Pattern.compile(regex);
       Matcher m = p.matcher(text);
        return m.matches();
    }


    public static boolean isEmailValid(String email) {
        final String regex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;" +
                ":\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
       Pattern p = java.util.regex.Pattern.compile(regex);
       Matcher m = p.matcher(email);
        return m.matches();
    }
}