package model.service;

import model.entity.Menu;
import model.entity.Order;

import java.util.List;

public interface OrderService extends GenenralService<Order> {
    void setDish(Order order, List<Menu> menuList);


}
