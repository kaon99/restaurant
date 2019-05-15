package model.dao.daoimpl;

import model.dao.connectionpool.ConnectionPool;
import model.dao.daointerface.BillDao;
import model.dao.daointerface.MenuDao;
import model.dao.daointerface.OrderDao;
import model.dao.daointerface.UserDao;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static Logger logger = Logger.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource = ConnectionPool.getDataSource();

    @Override
    public BillDao createBillDao() {
        return new BillDaoImpl(getConnection());
    }

    @Override
    public MenuDao createMenuDao() {
        return new MenuDaoImpl(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new OrderDaoImpl(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Failed establishing connection to database", e);
            throw new RuntimeException(e);
        }

    }
}
