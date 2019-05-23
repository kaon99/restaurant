package controller.util;

public class CommandUtil {


    public static String getUserPageByRole(int accessLevel) {
        String page = "";
        switch (accessLevel) {
            case 2:
                page = "redirect:/client";
                break;
            case 3:
                page = "redirect:/admin";
                break;
            default:
        }
        return page;

    }
}
