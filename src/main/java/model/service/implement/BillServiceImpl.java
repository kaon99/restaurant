package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.BillDao;
import model.dao.daointerface.OrderDao;
import model.entity.Bill;
import model.entity.types.Status;
import model.service.BillService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class BillServiceImpl implements BillService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public Bill create(Bill entity) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            logger.info("Create menu ");
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

            logger.info("Find all bills");
            return billDao.findAll();
        }
    }

    @Override
    public void update(Bill entity) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            billDao.update(entity);
            logger.info("bill update ");
        }
    }

    @Override
    public void delete(int id) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            billDao.delete(id);
            logger.info("delete bill");
        }
    }

    @Override
    public void createBillWithSum(int orderId) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            billDao.createBillWithSum(orderId);
        }
        logger.info("create bill with sum");
    }

    @Override
    public List<Bill> unpaidList(int userId) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            logger.info("create bill with sum");
            return billDao.unpaidList(userId);
        }
    }

    @Override
    public void pay(int billId) {
        try (BillDao billDao = daoFactory.createBillDao()) {
            billDao.pay(billId, Status.PAID.getStatus());
            logger.fatal("set status");
        }
    }
}
