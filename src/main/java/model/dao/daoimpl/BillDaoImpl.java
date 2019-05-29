package model.dao.daoimpl;

import model.dao.daointerface.BillDao;
import model.dao.mapper.BillMapper;
import model.dao.queriesManager.QueriesResourceManager;
import model.entity.Bill;
import model.entity.types.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.create"), Statement.RETURN_GENERATED_KEYS)) {
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
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.find.by.id"))) {
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
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.find.all"))) {
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
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.update"))) {
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
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.delete"))) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.info("Bill don`t delete ", e);
        }
    }

    @Override
    public void createBillWithSum(int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.find.sum"))) {
            connection.setAutoCommit(false);
            Bill bill = new Bill(new Date(Calendar.getInstance().getTime().getTime()), Status.UNPAID.getStatus(), orderId);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bill.setUserId(resultSet.getInt(1));
                bill.setSum(resultSet.getInt(2));

            }
            if (Objects.isNull(bill)) {
                connection.rollback();
            }
            try (PreparedStatement statementInsert = connection.prepareStatement("INSERT into resstaurant.bill (date, sum,status,user_user_id,order_order_id) value (?,?,?,?,?);")) {
                statementInsert.setDate(1, bill.getDate());
                statementInsert.setInt(2, bill.getSum());
                statementInsert.setInt(3, bill.getStatus());
                statementInsert.setInt(4, bill.getUserId());
                statementInsert.setInt(5, bill.getOrderId());
                statementInsert.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);

            }
        } catch (SQLException e) {
            logger.info("Bill don`t created ", e);
            e.printStackTrace();
        }
    }

    @Override
    public void pay(int billID, int status) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.pay"))) {
            statement.setInt(1, status);
            statement.setInt(2, billID);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Cannot set status", e);
        }
    }

    @Override
    public List<Bill> unpaidList(int userId) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.paid.list"))) {
            statement.setInt(1,0);
            statement.setInt(1, userId);

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
    public List<Bill> paidList(int userId) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("bill.paid.list"))) {
            statement.setInt(1,1);
            statement.setInt(2, userId);

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
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Close ", e);
        }
    }

}

