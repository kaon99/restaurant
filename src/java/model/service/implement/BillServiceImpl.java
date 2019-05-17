package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.BillDao;
import model.dao.daointerface.OrderDao;
import model.entity.Bill;
import model.service.BillService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class BillServiceImpl implements BillService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public Bill create(Bill entity) {
        try (BillDao billDao = daoFactory.createBillDao();
             OrderDao orderDao = daoFactory.createOrderDao()) {

          //  Bill bill = new Bill(LocalDateTime.now(), );


            logger.info("Create menu = %d");
            return billDao.create(entity);
        }
    }

    @Override
    public Bill findById(int id) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            Bill bill = billDao.findById(id);
            logger.info("Find bill by id ");
            return bill;
        }
    }

    @Override
    public List<Bill> findAll() {
        try (BillDao billDao = daoFactory.createBillDao()) {
            List<Bill> bills = billDao.findAll();
            logger.info("Find all bills");
            return bills;
        }
    }

    @Override
    public void update(Bill entity) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            billDao.update(entity);
            logger.info("bill update %d");
        }
    }

    @Override
    public void delete(int id) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            billDao.delete(id);
            logger.info("delete bill");
        }
    }
}
