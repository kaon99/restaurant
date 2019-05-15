package model.dao.mapper;

import model.entity.Bill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BillMapper implements ObjectMapper<Bill> {
    @Override
    public Bill extractFromResultSet(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getInt("bill_id"));
        bill.setDate(rs.getDate("date"));
        bill.setStatus(rs.getInt("status"));
        bill.setOrderId(rs.getInt("order_order_id"));
        bill.setSum(rs.getInt("sum"));
        bill.setUserId(rs.getInt("user_user_id"));
        return bill;
    }

    @Override
    public Bill makeUnique(Map<Integer, Bill> cache, Bill bill) {
        cache.putIfAbsent(bill.getId(),bill);
        return cache.get(bill.getId());
    }
}



