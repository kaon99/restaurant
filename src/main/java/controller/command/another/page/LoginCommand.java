package controller.command.another.page;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.CommandUtil;
import controller.util.ContextUtil;
import controller.util.PageResourseManager;
import model.entity.User;
import model.service.UserService;
import model.service.implement.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginCommand implements Command {
    private static Logger logger = Logger.getLogger(LoginCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(AttributesResourseManager.getProperty("parameter.login"));
        String password = request.getParameter(AttributesResourseManager.getProperty("parameter.password"));
        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            UserService userService = new UserServiceImpl();


            User user = userService.loginUser(login, password);
            if(ContextUtil.isUserInContext(request.getSession(),user)){
                ContextUtil.logoutUser(user);
            }
            request.getSession().setAttribute(AttributesResourseManager.getProperty("parameter.user"), user);
            ContextUtil.setAttributesToContext(request.getSession(),user);
            logger.info("user login");

            if (Objects.nonNull(user)) {
                String page = CommandUtil.getUserPageByRole(user.getRole());
                return page;
            }
            else  {
                request.setAttribute(AttributesResourseManager.getProperty("parameter.not.found"), true);
            }
        }

        return PageResourseManager.getProperty("login");
    }
}
