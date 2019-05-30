package model.service.implement;

import model.dao.daoImpl.DaoFactory;
import model.dao.daoInterface.BillDao;
import model.entity.Bill;
import model.entity.types.Status;
import model.service.BillService;
import org.apache.log4j.Logger;

import java.util.List;

public class BillServiceImpl implements BillService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    BillDao billDao = daoFactory.createBillDao();

    @Override
    public Bill create(Bill entity) {
        logger.info("Create menu ");
        return billDao.create(entity);

    }

    @Override
    public Bill findById(int id) {
        Bill bill = billDao.findById(id);
        logger.info("Find bill by id ");
        return bill;

    }

    @Override
    public List<Bill> findAll() {
        logger.info("Find all bills");
        return billDao.findAll();

    }

    @Override
    public void update(Bill entity) {
        billDao.update(entity);
        logger.info("bill update ");

    }

    @Override
    public void delete(int id) {
        billDao.delete(id);
        logger.info("delete bill");

    }

    @Override
    public void createBillWithSum(int orderId) {
        billDao.createBillWithSum(orderId);
        logger.info("create bill with sum");
    }

    @Override
    public List<Bill> unpaidList(int userId) {
        logger.info("create bill with sum");
        return billDao.unpaidList(userId);
    }

    @Override
    public void pay(int billId) {
        billDao.pay(billId, Status.PAID.getStatus());
        logger.fatal("set status");
    }
}
