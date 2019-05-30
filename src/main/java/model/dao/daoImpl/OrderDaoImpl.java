package model.dao.daoImpl;


import model.dao.connectionPool.ConnectionPool;
import model.dao.daoInterface.OrderDao;
import model.dao.mapper.OrderMapper;
import model.dao.queriesManager.QueriesResourceManager;
import model.entity.Menu;
import model.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Logger logger = Logger.getLogger(BillDaoImpl.class);

    @Override
    public Order create(Order entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.create"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getNote());
            statement.setInt(2, entity.getUserId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            logger.info("Do not create order", e);
        }
        return entity;
    }

    @Override
    public Order findById(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.find.by.id"))) {

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
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.find.all"))) {
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
    public List<Order> findAllUnpaid() {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.find.all.unpaid"))) {
            ResultSet resultSet = statement.executeQuery();
            List orders = new ArrayList();
            while (resultSet.next()) {
                orders.add(new OrderMapper().extractFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            logger.info("Cannot find All unpaid", e);
        }
        return null;
    }

    @Override
    public List<Order> findOrdersPagination(int start, int recordsPerPage) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.find.pagination"))) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(new OrderMapper().extractFromResultSet(resultSet));
            }

            return orders;
        } catch (SQLException e) {
            logger.info("Cannot find orders on page", e);
            return null;
        }
    }

    @Override
    public int getNumberOfRows() {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.get.number"))) {
            int numOfRows = 0;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numOfRows = resultSet.getInt(1);
            }

            return numOfRows;
        } catch (SQLException e) {
            logger.info("Cannot find number of rows", e);
        }
        return 0;

    }

    @Override
    public void update(Order entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.update"))) {
            statement.setString(1, entity.getNote());
            statement.setInt(2, entity.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Cannot update order", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.delete"))) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Order don`t delete ", e);
        }
    }

    @Override
    public void setDish(int orderId, List<Menu> menuList) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("order.set.dish"))) {

            for (Menu menu : menuList) {
                statement.setInt(1, orderId);
                statement.setInt(2, menu.getId());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            logger.info("Order don`t set ", e);
        }
    }

}

