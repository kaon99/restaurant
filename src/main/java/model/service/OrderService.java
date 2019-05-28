package model.service;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */


import model.entity.Menu;
import model.entity.Order;

import java.util.List;

public interface OrderService extends GenenralService<Order> {
    void setDish(Order order, List<Menu> menuList);
    List<Order> findAllUnpaid ();
    List<Order> findOrdersPagination(int currentPage, int recordsPerPage);
    int getNumberOfRows();
}
