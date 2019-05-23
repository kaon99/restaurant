package model.dao.daointerface;

import model.entity.Bill;
import model.entity.types.Status;

import java.util.List;

public interface BillDao extends GenericDao<Bill> {
    void createBillWithSum(int orderId);
    void pay(int billID, int status);
    List<Bill> unpaidList(int userId);

    List<Bill> paidList(int userId);
}
