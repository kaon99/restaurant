package controller.util;

import java.util.ResourceBundle;

public class PageResourseManager {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("pages");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
