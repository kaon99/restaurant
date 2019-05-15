package controller.command.another.action;

import controller.command.Command;
import controller.command.another.page.RegistrationCommandPage;
import controller.command.validation.ValidationUtil;
import controller.util.AttributesResourseManager;
import controller.util.CommandUtil;
import model.entity.User;
import model.entity.types.Role;
import model.exception.UserExistException;
import model.exception.WrongDataException;
import model.service.UserService;
import model.service.implement.UserServiceImpl;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static Logger logger = Logger.getLogger(RegistrationCommand.class);



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        ValidationUtil validationUtil = new ValidationUtil();
        try {
            String name = request.getParameter("name");
            String email = request.getParameter(AttributesResourseManager.getProperty("parameter.email"));
            String password = request.getParameter(AttributesResourseManager.getProperty("parameter.password"));

            Integer role = Role.CLIENT.getRole();
            if (!validationUtil.verificate(name,email)) {
                throw new WrongDataException();
            }
            if (!validationUtil.userExist(email)) {
                throw new UserExistException();
            }
User user = new User(name,password,email,role);
            userService.create(user);
            CommandUtil.getUserPageByRole(user.getRole());
        } catch (WrongDataException e) {
            logger.error(e);
            request.setAttribute("registrationError", true);

        } catch (UserExistException e) {
            logger.error(e);
            request.setAttribute("userExist", true);

        }
        return new RegistrationCommandPage().execute(request, response);
    }
}