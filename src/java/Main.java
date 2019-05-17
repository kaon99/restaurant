
import model.entity.Bill;
import model.entity.Menu;
import model.entity.Order;
import model.entity.User;
import model.service.MenuService;
import model.service.OrderService;
import model.service.UserService;
import model.service.implement.MenuServiceImpl;
import model.service.implement.OrderServiceImpl;
import model.service.implement.UserServiceImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        MenuService menuService = new MenuServiceImpl();
        String[] ss = {"1","2","5"};
     //   menuService.chooseDishes(ss).stream().forEach(System.out::println);
      //  Bill bill = new Bill(,1,2,1,1);
       // System.out.println(bill);
    }
}
