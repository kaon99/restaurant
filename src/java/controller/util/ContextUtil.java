package controller.util;



import model.entity.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class ContextUtil {
    private static Map<User, HttpSession> loggedUsers;

    public static boolean isUserInContext(HttpSession session, User user) {
        getLoggedUsers(session);
        return loggedUsers.keySet().contains(user);
    }

    public static void logoutUser(User user) {
        HttpSession oldSession = loggedUsers.get(user);
        oldSession.invalidate();
    }

    public static void setAttributesToContext(HttpSession session, User user) {
        getLoggedUsers(session);
        loggedUsers.put(user, session);
    }

    @SuppressWarnings("unchecked")
    private static void getLoggedUsers(HttpSession session) {
        loggedUsers = (HashMap<User, HttpSession>) session.getServletContext().getAttribute(
                AttributesResourseManager.getProperty("attribute.servlet.context.logged.users"));
    }
}
