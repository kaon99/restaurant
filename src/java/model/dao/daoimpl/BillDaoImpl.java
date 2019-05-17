package model.dao.daoimpl;

import model.dao.daointerface.BillDao;
import model.dao.mapper.BillMapper;
import model.entity.Bill;
import model.entity.Order;
import model.entity.types.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillDaoImpl implements BillDao {
    private Logger logger = Logger.getLogger(BillDaoImpl.class);
    private Connection connection;

    public BillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bill create(Bill entity) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT into resstaurant.bill (bill.date, bill.sum,bill.status,bill.user_user_id,bill.order_order_id) value (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, entity.getDate());
            statement.setInt(2, entity.getSum());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getUserId());
            statement.setInt(5, entity.getOrderId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            logger.info("Bill do not create", e);
        }
        return entity;
    }

    @Override
    public Bill findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM resstaurant.bill where bill_id = ?")) {
            Bill bill = null;
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BillMapper billMapper = new BillMapper();
                bill = billMapper.extractFromResultSet(resultSet);
            }
            return bill;
        } catch (SQLException e) {
            logger.info("Bill do not find by Id", e);

        }
        return null;
    }

    @Override
    public List<Bill> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("select * FROM resstaurant.bill")) {
            ResultSet resultSet = statement.executeQuery();
            List bills = new ArrayList();
            while (resultSet.next()) {
                bills.add(new BillMapper().extractFromResultSet(resultSet));
            }
            return bills;
        } catch (SQLException e) {
            logger.info("Cannot find All", e);
        }
        return null;
    }

    @Override
    public void update(Bill entity) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE  resstaurant.bill set user_user_id =? ,order_order_id = ?, status = ? , sum = ? , date = ? where bill_id = ?")) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getOrderId());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getSum());
            statement.setDate(5, entity.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Cannot update bill", e);
        }

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM resstaurant.bill where bill_id = ? ")) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.info("Bill don`t delete ", e);
        }
    }

    @Override
    public void createBillWithSum(int orderId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT SUM (resstaurant.dish)")) {
            connection.setAutoCommit(false);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            int sumOfResult = 0;
            int userId = 0;
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
                sumOfResult = resultSet.getInt(2);

            }
            if (Objects.isNull(sumOfResult) || Objects.isNull(userId)) {
                connection.rollback();
            }
          //  Bill bill = new Bill(, Status.UNPAID.getStatus(),sumOfResult,userId,orderId);
//            try (PreparedStatement statmentInsert) {
//            }
        } catch (SQLException e) {
            logger.info("Bill don`t created ", e);
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

