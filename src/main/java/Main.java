
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
import java.util.*;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuServiceImpl();
        Optional<String [] > optionalStrings ;
        optionalStrings = Optional.ofNullable(null);



        String [] dishList = {"1","2","4"};

        int[] array = Arrays.stream(optionalStrings.orElse(dishList)).mapToInt(Integer::parseInt).toArray();

        List<Menu> menus = new ArrayList<>();
        List<Menu> allMenus = menuService.findAll();

        for (Menu menu : menuService.findAll()) {
            for (int i : array) {
                if (menu.getId().equals(i)) {
                    menus.add(menu);
                }
            }
        }menus.stream().forEach(System.out::println);
    }
}