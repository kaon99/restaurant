package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.MenuDao;
import model.dao.daointerface.OrderDao;
import model.dao.daointerface.UserDao;
import model.entity.Menu;
import model.entity.Order;
import model.entity.User;
import model.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public void create(Order entity) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.create(entity);
            logger.info("Create order = %d");
        }
    }

    @Override
    public Order findById(int id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            Order order = orderDao.findById(id);
            logger.info("Find student by id ");
            return order;
        }
    }

    @Override
    public List<Order> findAll() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            List<Order> orders = orderDao.findAll();
            logger.info("Find all orders");
            return orders;
        }
    }

    @Override
    public void update(Order entity) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.update(entity);
            logger.info("order update %d");
        }
    }

    @Override
    public void delete(int id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.delete(id);
            logger.info("delete order");
        }
    }

    @Override
    public void setDish(Order order, Menu menu) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.setDish(order.getId(), menu.getId());
            logger.info("dish  has selected");
        }
    }
}