package model.service.implement;

import model.dao.daoImpl.DaoFactory;
import model.dao.daoInterface.OrderDao;
import model.entity.Menu;
import model.entity.Order;
import model.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    OrderDao orderDao = daoFactory.createOrderDao();

    @Override
    public Order create(Order entity) {
            logger.info("Create order ");
            return orderDao.create(entity);
    }

    @Override
    public Order findById(int id) {
            Order order = orderDao.findById(id);
            logger.info("Find student by id ");
            return order;
    }

    @Override
    public List<Order> findAll() {
            List<Order> orders = orderDao.findAll();
            logger.info("Find all orders");
            return orders;
    }

    @Override
    public void update(Order entity) {
            orderDao.update(entity);
            logger.info("order update ");
    }

    @Override
    public void delete(int id) {
            orderDao.delete(id);
            logger.info("delete order");
    }

    @Override
    public void setDish(Order order, List<Menu> menuList) {
            if(!menuList.isEmpty()){
            orderDao.setDish(order.getId(), menuList);
            logger.info("dish  has selected");
    }}

    @Override
    public List<Order> findAllUnpaid() {
            List<Order> orders = orderDao.findAllUnpaid();
            logger.info("Find all orders");
            return orders;
    }

    @Override
    public List<Order> findOrdersPagination(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
         logger.info("find pages");
            return orderDao.findOrdersPagination(start, recordsPerPage);
        }

    @Override
    public int getNumberOfRows() {
            logger.info("get number of page");
            return orderDao.getNumberOfRows();
    }
}

