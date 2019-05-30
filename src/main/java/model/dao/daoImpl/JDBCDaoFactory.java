package model.dao.daoImpl;

import model.dao.connectionPool.ConnectionPool;
import model.dao.daoInterface.BillDao;
import model.dao.daoInterface.MenuDao;
import model.dao.daoInterface.OrderDao;
import model.dao.daoInterface.UserDao;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static Logger logger = Logger.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource = ConnectionPool.getDataSource();

    @Override
    public BillDao createBillDao() {
        return new BillDaoImpl();
    }

    @Override
    public MenuDao createMenuDao() {

        return new MenuDaoImpl();
    }

    @Override
    public OrderDao createOrderDao() {
        return new OrderDaoImpl();
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
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
