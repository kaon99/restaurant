package controller.command.admin.page;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.service.BillService;
import model.service.OrderService;
import model.service.implement.BillServiceImpl;
import model.service.implement.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageBillCommand implements Command {
  private   OrderService orderService = new OrderServiceImpl();
  private   Logger logger = Logger.getLogger(AdminPageBillCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(AttributesResourseManager.getProperty("parameter.order.list"),orderService.findAllUnpaid());

        logger.info("execute");
        return PageResourseManager.getProperty("admin.bill");
    }
}
