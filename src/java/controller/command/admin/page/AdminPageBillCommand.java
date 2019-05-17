package controller.command.admin.page;

import controller.command.Command;
import controller.util.PageResourseManager;
import model.service.OrderService;
import model.service.implement.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCommandPageBill implements Command {
    OrderService orderService = new OrderServiceImpl();
    Logger logger = Logger.getLogger(AdminCommandPageBill.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("orderList",orderService.findAll());
        logger.info("execute");
        return PageResourseManager.getProperty("admin.bill");
    }
}
