package model.dao.daointerface;



import model.entity.Order;

public interface OrderDao extends GenericDao<Order>{
     void setDish (int orderId, int menuId );

}
