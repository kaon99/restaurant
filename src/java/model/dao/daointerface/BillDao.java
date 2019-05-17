package model.dao.daointerface;

import model.entity.Bill;
import model.entity.Order;

public interface BillDao  extends GenericDao<Bill>{
    void createBillWithSum (int orderId);
}
