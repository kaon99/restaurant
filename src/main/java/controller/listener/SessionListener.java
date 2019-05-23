package controller.listener;

import controller.util.AttributesResourseManager;
import model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user = (User) session.getAttribute(AttributesResourseManager.getProperty("parameter.user"));
        ServletContext servletContext = session.getServletContext();
        Map<User, HttpSession> loggedUsers =
                (HashMap<User, HttpSession>) servletContext.getAttribute(
                        AttributesResourseManager.getProperty("attribute.servlet.context.logged.users"));
        loggedUsers.remove(user);
    }
}