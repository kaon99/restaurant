package model.service.implement;

import model.dao.daoImpl.DaoFactory;
import model.dao.daoImpl.MenuDaoImpl;
import model.entity.Menu;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class MenuServiceImplTest {
    @InjectMocks
    private MenuServiceImpl menuService;
    private Menu menu;
    @Mock
    private MenuDaoImpl menuDao;
    @Mock
    private DaoFactory daoFactory;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        menu = new Menu("Borsh","Борщ",100);
        int [] list = {1};
        menu.setId(1);
        when(menuDao.findById(1)).thenReturn(menu);
        when(menuDao.findAll()).thenReturn(Collections.singletonList(menu));
        when(menuDao.findDishListById(list)).thenReturn(Collections.singletonList(menu));
        when(daoFactory.createMenuDao()).thenReturn(menuDao);


    }
    @Test
    public void create() {
      Menu  menuCreated = new Menu("Borsh","Борщ",100);
        menuCreated.setId(1);
        menuService.create(menu);
        verify(menuDao,times(1)).create(menu);
    }

    @Test
    public void findById() {
        Menu menuServiceById = menuService.findById(1);
        assertEquals(menu,menuServiceById);

    }

    @Test
    public void findAll() {
        List<Menu> menuList = menuService.findAll();
        assertEquals(menu,menuList.get(0));
    }

    @Test
    public void update() {
        Menu  menuCreated = new Menu("Borsh","Борщ",100);
        menuCreated.setId(1);
        menuService.update(menu);
        verify(menuDao,times(1)).update(menu);
    }

    @Test
    public void delete() {
        menuService.delete(2);
        verify(menuDao, times(1)).delete(2);

    }

    @Test
    public void chooseDishes() {
      String []dishList = {"1"};
     List <Menu> menuList=   menuService.chooseDishes(dishList,menuDao.findAll());
     assertEquals(menu,menuList.get(0));
    }
}