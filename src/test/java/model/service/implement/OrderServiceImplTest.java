package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daoimpl.OrderDaoImpl;
import model.entity.Menu;
import model.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    private Order order;
    @Mock
    private OrderDaoImpl orderDao;
    @Mock
    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        order = new Order("La",1,1);
        order.setId(1);
        when(orderDao.findById(1)).thenReturn(order);
        when(orderDao.findAll()).thenReturn(Collections.singletonList(order));
        when(orderDao.findAllUnpaid()).thenReturn(Collections.singletonList(order));
        when(orderDao.findOrdersPagination(0,5)).thenReturn(Collections.singletonList(order));
        when(orderDao.getNumberOfRows()).thenReturn(5);
        when(daoFactory.createOrderDao()).thenReturn(orderDao);

    }

    @Test
    public void create() {
Order order = new Order("La",1,1)  ;
        order.setId(1);
        orderService.create(order);
        verify(orderDao,times(1)).create(order);
    }

    @Test
    public void findById() {
        Order orderList = orderService.findById(1);
        assertEquals(order,orderList);

    }

    @Test
    public void findAll() {
        List<Order> orderList = orderService.findAll();
        assertEquals(order,orderList.get(0));
    }

    @Test
    public void update() {
        Order order = new Order("La",1,1)  ;
        order.setId(1);
        orderService.update(order);
        verify(orderDao,times(1)).update(order);
    }

    @Test
    public void delete() {
        orderService.delete(2);
        verify(orderDao, times(1)).delete(2);
    }

    @Test
    public void setDish() {
        Menu menu = new Menu("Borsh","Борщ",100);
        menu.setId(1);
        orderService.setDish(order,Collections.singletonList(menu));
      verify(orderDao,times(1)).setDish(order.getId(),Collections.singletonList(menu));
    }

    @Test
    public void findAllUnpaid() {
        List<Order> orderList = orderService.findAllUnpaid();
        assertEquals(order,orderList.get(0));
    }

    @Test
    public void findOrdersPagination() {
        List<Order> orderList = orderService.findOrdersPagination(1,5);
        assertEquals(order,orderList.get(0));
    }

    @Test
    public void getNumberOfRows() {
        int i = orderService.getNumberOfRows();
      assertEquals(orderDao.getNumberOfRows(),i);
    }
}