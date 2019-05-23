package controller.command.another.page;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.CommandUtil;
import controller.util.PageResourseManager;
import model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class MainCommand  implements Command {
    private static Logger logger = Logger.getLogger(MainCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        User user = (User) request.getSession().getAttribute(AttributesResourseManager.getProperty("parameter.user"));

        if (Objects.nonNull(user)) {
            return CommandUtil.getUserPageByRole(user.getRole());
        }
        logger.info("execute");
        return PageResourseManager.getProperty("redirect").concat("/login");
    }
}
