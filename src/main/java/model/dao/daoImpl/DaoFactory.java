package model.dao.daoImpl;

import model.dao.daoInterface.BillDao;
import model.dao.daoInterface.MenuDao;
import model.dao.daoInterface.OrderDao;
import model.dao.daoInterface.UserDao;
import org.apache.log4j.Logger;


public abstract class DaoFactory {
    protected static Logger logger = Logger.getLogger(DaoFactory.class);
private static DaoFactory daoFactory;

public abstract BillDao createBillDao ();
public abstract MenuDao createMenuDao();
public abstract OrderDao createOrderDao();
public abstract UserDao createUserDao();

public static DaoFactory getInstance (){
    if(daoFactory == null){
        synchronized (DaoFactory.class){
            if(daoFactory == null){
                daoFactory = new JDBCDaoFactory();
            }
        }
    }
    logger.info("dao factory");
    return daoFactory;
}



}
