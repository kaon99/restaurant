package controller.command.client.action;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.entity.Menu;
import model.entity.Order;
import model.entity.User;
import model.service.MenuService;
import model.service.OrderService;
import model.service.implement.MenuServiceImpl;
import model.service.implement.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateOrderCommand implements Command {
    Logger logger = Logger.getLogger(CreateOrderCommand.class);
    MenuService menuService = new MenuServiceImpl();
    OrderService orderService = new OrderServiceImpl();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Menu> allDish = menuService.findAll();
        String[] dishNamesToOrder = request.getParameterValues(AttributesResourseManager.getProperty("parameter.dish"));
        User user = (User) request.getSession().getAttribute(AttributesResourseManager.getProperty("parameter.user"));
        String note = request.getParameter(AttributesResourseManager.getProperty("parameter.note"));
        request.setAttribute("databaseList", allDish);
        Order order = orderService.create(new Order(note, user.getId()));
        orderService.setDish(order, menuService.chooseDishes(dishNamesToOrder, allDish));
        logger.info("execute");
        return PageResourseManager.getProperty("redirect.client.order");
    }
}
