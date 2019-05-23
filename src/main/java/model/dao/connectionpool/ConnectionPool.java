package model.dao.connectionpool;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class ConnectionPool {
    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        logger.error(e);
                    }
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/resstaurant?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setRemoveAbandonedTimeout(50);
                    dataSource = ds;
                }
            }

        }
        return dataSource;


    }

}

