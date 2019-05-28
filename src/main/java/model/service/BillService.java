package model.service;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

import model.entity.Bill;

import java.util.List;

public interface BillService extends GenenralService<Bill> {
    void createBillWithSum(int orderId);
    List<Bill> unpaidList(int userId);
    void pay(int billId);
}
