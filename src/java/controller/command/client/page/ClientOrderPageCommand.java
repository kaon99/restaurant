package controller.command.client.page;

import controller.command.Command;
import controller.util.PageResourseManager;
import model.service.MenuService;
import model.service.implement.MenuServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientOrderPage implements Command {
    Logger logger = Logger.getLogger(ClientOrderPage.class);
MenuService menuService = new MenuServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("databaseList", menuService.findAll());


        logger.info("execute");

        return PageResourseManager.getProperty("order");
    }
}
