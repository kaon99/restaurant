package controller.command.client.page;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.entity.User;
import model.service.BillService;
import model.service.implement.BillServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientBillPageCommand implements Command {
    Logger logger = Logger.getLogger(ClientBillPageCommand.class);
    BillService billService = new BillServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesResourseManager.getProperty("parameter.user"));
        request.setAttribute("billList", billService.unpaidList(user.getId()));
        logger.info("execute");
        return PageResourseManager.getProperty("client.pay.bill");
    }
}
