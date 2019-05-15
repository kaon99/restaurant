package model.dao.daoimpl;


import model.dao.daointerface.OrderDao;
import model.dao.mapper.OrderMapper;
import model.entity.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Logger logger = Logger.getLogger(BillDaoImpl.class);
    private Connection connection;

    public OrderDaoImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public void create(Order entity) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO resstaurant.order(Note, user_user_id) value (?,?")) {
            statement.setString(1, entity.getNote());
            statement.setInt(2, entity.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Do not create order", e);
        }
    }

    @Override
    public Order findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM resstaurant.order where order_id = ?")) {

            Order order = null;
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                OrderMapper orderMapper = new OrderMapper();
                order = orderMapper.extractFromResultSet(resultSet);

            }
            return order;
        } catch (SQLException e) {
            logger.info("Do not find order", e);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("select * from resstaurant.order ")) {
            ResultSet resultSet = statement.executeQuery();
            List orders = new ArrayList();
            while (resultSet.next()) {
                orders.add(new OrderMapper().extractFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            logger.info("Cannot find All", e);
        }
        return null;
    }

    @Override
    public void update(Order entity) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE resstaurant.order set Note= ?,user_user_id = ? where order.order_id = ? ")) {
            statement.setString(1, entity.getNote());
            statement.setInt(2, entity.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Cannot update order", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM resstaurant.order where order_id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Order don`t delete ", e);
        }
    }

    @Override
    public void setDish(int orderId, int menuId) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO resstaurant.order_has_menu (order_order_id, menu_menu_id) value (?,?)")) {
            statement.setInt(1, orderId);
            statement.setInt(2, menuId);


        } catch (SQLException e) {
            logger.info("Order don`t set ", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Close ", e);
        }
    }


}

