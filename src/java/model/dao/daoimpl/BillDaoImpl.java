package model.dao.daoimpl;

import model.dao.daointerface.BillDao;
import model.dao.mapper.BillMapper;
import model.entity.Bill;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    private Logger logger = Logger.getLogger(BillDaoImpl.class);
    private Connection connection;

    public BillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Bill entity) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT into resstaurant.bill (bill.date, bill.sum,bill.status,bill.user_user_id,bill.order_order_id) value (?,?,?,?,?);")) {
            statement.setDate(1, entity.getDate());
            statement.setInt(2, entity.getSum());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getUserId());
            statement.setInt(5, entity.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Bill do not create", e);
        }

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
        try (PreparedStatement statement = connection.prepareStatement("select * FROM resstaurant.bill")){
            ResultSet resultSet = statement.executeQuery();
            List bills = new ArrayList();
            while (resultSet.next()){
                bills.add(new BillMapper().extractFromResultSet(resultSet));
            }
            return bills;
        } catch (SQLException e) {
            logger.info("Cannot find All",e);
        }
        return null;
    }

    @Override
    public void update(Bill entity) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE  resstaurant.bill set user_user_id =? ,order_order_id = ?, status = ? , sum = ? , date = ? where bill_id = ?")){
            statement.setInt(1,entity.getUserId());
            statement.setInt(2,entity.getOrderId());
            statement.setInt(3,entity.getStatus());
            statement.setInt(4,entity.getSum());
            statement.setDate(5,entity.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Cannot update bill",e);
        }

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM resstaurant.bill where bill_id = ? ")) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.info("Bill don`t delete ",e);
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

    @Override
    public void createBillWithSum(Bill bill) {

    }
}

