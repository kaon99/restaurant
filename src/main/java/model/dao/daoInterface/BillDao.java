package model.dao.daoInterface;

import model.entity.Bill;

import java.util.List;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

public interface BillDao extends GenericDao<Bill> {
    /**
     * transaction where create bill.
     * @param orderId
     * */
    void createBillWithSum(int orderId);
    /**user pay a bill
     * @param billID
     * @param status
     * */
    void pay(int billID, int status);
    /**
     * @param userId
     *@return  unpaid list
     * */
    List<Bill> unpaidList(int userId);
/**
 * @return paid list
 * @param userId*/
    List<Bill> paidList(int userId);
}
