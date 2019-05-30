package model.service.implement;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.MenuDao;
import model.dao.daointerface.OrderDao;
import model.dao.daointerface.UserDao;
import model.entity.Menu;
import model.entity.Order;
import model.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public Order create(Order entity) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            logger.info("Create order ");
            return orderDao.create(entity);

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
    public void setDish(Order order, List<Menu> menuList) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            if(!menuList.isEmpty()){
            orderDao.setDish(order.getId(), menuList);
            logger.info("dish  has selected");
        }
    }}

    @Override
    public List<Order> findAllUnpaid() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            List<Order> orders = orderDao.findAllUnpaid();
            logger.info("Find all orders");
            return orders;
        }
    }

    @Override
    public List<Order> findOrdersPagination(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            logger.info("find pages");
            return orderDao.findOrdersPagination(start, recordsPerPage);
        }

    }

    @Override
    public int getNumberOfRows() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            logger.info("get number of page");
            return orderDao.getNumberOfRows();
        }
    }
}

