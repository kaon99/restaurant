package model.service;

import model.entity.Menu;
import model.entity.Order;

public interface OrderService extends GenenralService<Order> {
    void setDish(Order order, Menu menu) ;

}
