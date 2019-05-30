package model.dao.daoInterface;


/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

import model.entity.Menu;
import model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{
     /**
      * set dishes in order
      * @param  orderId
      * @param menuList
      * */
     void setDish(int orderId, List<Menu> menuList);
     /**
      *@return  find all unpaid orders
      * */
     List<Order> findAllUnpaid();
     /**
     * @return find orders in current page
      * @param currentPage
      * @param recordsPerPage
     * */
     List<Order> findOrdersPagination(int currentPage, int recordsPerPage);
    /**
     * @return sum of numbers
     * */
     int getNumberOfRows();


}
