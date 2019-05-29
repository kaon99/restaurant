package controller.command.client.action;

import controller.command.Command;
import controller.command.client.page.ClientBillPageCommand;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.entity.User;
import model.service.BillService;
import model.service.implement.BillServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayBillCommand  implements Command {
   private Logger logger = Logger.getLogger(ClientBillPageCommand.class);
     private BillService billService = new BillServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {


            User user = (User) request.getSession().getAttribute(AttributesResourseManager.getProperty("parameter.user"));
            request.setAttribute(AttributesResourseManager.getProperty("parameter.bill.list"), billService.unpaidList(user.getId()));
            int billId = Integer.parseInt(request.getParameter(AttributesResourseManager.getProperty("parameter.bill")));
            billService.pay(billId);
        }
        catch (NumberFormatException e) {
            logger.info("Dont choose any order",e);
            return PageResourseManager.getProperty("redirect.client.bill");
        }
        logger.info("execute");
        return PageResourseManager.getProperty("redirect.client.bill");
    }
}
