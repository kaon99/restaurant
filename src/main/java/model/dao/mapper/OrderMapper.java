package model.dao.mapper;

import model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setNote(rs.getString("Note"));
        order.setUserId(rs.getInt("user_user_id"));
        return order;
    }

    @Override
    public Order makeUnique(Map<Integer, Order> cache, Order order) {
        cache.putIfAbsent(order.getId(),order);
        return cache.get(order.getId());
    }
}
