package model.service.implement;



import model.dao.daoimpl.BillDaoImpl;
import model.dao.daoimpl.DaoFactory;
import model.entity.Bill;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class BillServiceImplTest {
    @InjectMocks
    private BillServiceImpl billService;
    private Bill bill;
    @Mock
    private BillDaoImpl billDao;
    @Mock
    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bill = new Bill( new Date(Calendar.getInstance().getTime().getTime()),100,0,1,1);
    bill.setId(1);
        when(billDao.findById(1)).thenReturn(bill);
        when(billDao.findAll()).thenReturn(Collections.singletonList(bill));
        when(billDao.unpaidList(1)).thenReturn(Collections.singletonList(bill));
        when(daoFactory.createBillDao()).thenReturn(billDao);

    }

    @Test
    public void create() {
     Bill bill = new Bill( new Date(Calendar.getInstance().getTime().getTime()),100,0,1,1);
        bill.setId(1);
        billService.create(bill);
        verify(billDao,times(1)).create(bill);
    }

    @Test
    public void findById() {
        Bill billFind = billService.findById(1);
        assertEquals(bill,billFind);

    }

    @Test
    public void findAll() {
        List<Bill> billList = billService.findAll();
        assertEquals(bill,billList.get(0));
    }

    @Test
    public void update() {
        Bill bill = new Bill( new Date(Calendar.getInstance().getTime().getTime()),100,0,1,1);
        bill.setId(1);
        billService.update(bill);
        verify(billDao,times(1)).update(bill);
    }

    @Test
    public void delete() {
        billService.delete(2);
        verify(billDao, times(1)).delete(2);
    }

    @Test
    public void createBillWithSum() {
        billService.createBillWithSum(1);
        verify(billDao,times(1)).createBillWithSum(1);
    }

    @Test
    public void unpaidList() {
        List<Bill> billList = billService.unpaidList(1);
        assertEquals(bill,billList.get(0));
    }

    @Test
    public void pay() {
        billService.pay(1);
        verify(billDao, times(1)).pay(1,1);

    }
}