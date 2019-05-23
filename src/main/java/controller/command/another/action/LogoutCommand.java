package controller.command.another.action;


import controller.command.Command;
import controller.util.PageResourseManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        request.getSession().invalidate();
        logger.info("execute");
        return PageResourseManager.getProperty("redirect").concat("/login");
    }
}
