package model.dao.daointerface;



import model.entity.Menu;
import model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{
     void setDish (int orderId, List<Menu> menuList);
     List<Order> findAllUnpaid();
     List<Order> findOrdersPagination(int currentPage, int recordsPerPage);
     int getNumberOfRows();


}
