package controller.command.admin.action;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.entity.Bill;
import model.service.BillService;
import model.service.OrderService;
import model.service.implement.BillServiceImpl;
import model.service.implement.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


public class CreateBillCommand implements Command {
    OrderService orderService = new OrderServiceImpl();
    BillService billService = new BillServiceImpl();
    Logger logger = Logger.getLogger(CreateBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("orderList", orderService.findAll());
int orderId = Integer.parseInt(request.getParameter(AttributesResourseManager.getProperty("order")));

        logger.info("execute");
        return PageResourseManager.getProperty("admin.bill");
    }
}