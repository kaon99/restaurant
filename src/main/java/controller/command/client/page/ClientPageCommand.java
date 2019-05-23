package controller.command.client.page;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ClientPageCommand implements Command {
    Logger logger = Logger.getLogger(ClientPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesResourseManager.getProperty("parameter.user"));
        request.setAttribute("name", user.getName());
        request.setAttribute(AttributesResourseManager.getProperty("parameter.email"), user.getEmail());
        logger.info("execute");
        return PageResourseManager.getProperty("client");
    }


}

