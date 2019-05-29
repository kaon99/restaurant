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
import java.util.NoSuchElementException;
import java.util.Optional;

public class CreateOrderCommand implements Command {

    private Logger logger = Logger.getLogger(CreateOrderCommand.class);
    private MenuService menuService = new MenuServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private Optional<String[]> dishNamesToOrder;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Menu> allDish = menuService.findAll();

        User user = (User) request.getSession().getAttribute(AttributesResourseManager.getProperty("parameter.user"));
        String note = request.getParameter(AttributesResourseManager.getProperty("parameter.note"));
        request.setAttribute(AttributesResourseManager.getProperty("parameter.database.list"), allDish);
        dishNamesToOrder = Optional.ofNullable(request.getParameterValues(AttributesResourseManager.getProperty("parameter.dish")));
        if(dishNamesToOrder.isPresent()){
            Order order = orderService.create(new Order(note, user.getId()));
            orderService.setDish(order, menuService.chooseDishes(dishNamesToOrder.get(), allDish));

        }

        logger.info("execute");
        return PageResourseManager.getProperty("redirect.client.order");
    }
}
