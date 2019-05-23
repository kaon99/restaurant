
import model.dao.daoimpl.DaoFactory;
import model.dao.daoimpl.OrderDaoImpl;
import model.dao.daointerface.BillDao;
import model.dao.daointerface.OrderDao;
import model.entity.Bill;
import model.entity.Menu;
import model.entity.Order;
import model.entity.User;
import model.service.BillService;
import model.service.MenuService;
import model.service.OrderService;
import model.service.UserService;
import model.service.implement.BillServiceImpl;
import model.service.implement.MenuServiceImpl;
import model.service.implement.OrderServiceImpl;
import model.service.implement.UserServiceImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderService orderService=  new  OrderServiceImpl();
       orderService.findOrdersPagination(1,4).stream().forEach(System.out::println);
    }
}
