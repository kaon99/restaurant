package controller.command.admin.action;

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


public class CreateBillCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();
  private   BillService billService = new BillServiceImpl();
   private static Logger logger = Logger.getLogger(CreateBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(AttributesResourseManager.getProperty("parameter.order.list"), orderService.findAllUnpaid());
            int orderId = Integer.parseInt(request.getParameter(AttributesResourseManager.getProperty("parameter.order")));


            billService.createBillWithSum(orderId);
        } catch (NumberFormatException e) {
            logger.info("Dont choose any order", e);
            return PageResourseManager.getProperty("redirect.admin.bill");
        }

        logger.info("execute");
        return PageResourseManager.getProperty("redirect.admin.bill");
    }
}