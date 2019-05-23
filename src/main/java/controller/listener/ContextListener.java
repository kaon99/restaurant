package controller.listener;



import model.entity.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import controller.util.AttributesResourseManager;
import java.util.HashMap;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().addListener(new SessionListener());
        servletContextEvent.getServletContext().setAttribute(
                AttributesResourseManager.getProperty("attribute.servlet.context.logged.users"),
                new HashMap<User, HttpSession>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
