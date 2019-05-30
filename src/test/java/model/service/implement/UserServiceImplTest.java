package model.service.implement;

import model.dao.daoImpl.DaoFactory;
import model.dao.daoInterface.UserDao;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @Mock
    private UserDao userDao;
    @Mock
    private DaoFactory daoFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User("name", "password", "email", 2);
        user.setId(1);
        when(daoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findById(0)).thenReturn(user);
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));
        when(userDao.findByEmail("email")).thenReturn(user);
        when(userDao.getByLoginAndPass("name", "password")).thenReturn(user);
    }


    @Test
    public void create() {
        User user = new User();
        user.setId(2);
        userService.create(user);
        verify(userDao, times(1)).create(user);
    }

    @Test
    public void findById() {
        User userFind = userService.findById(0);
        assertEquals(user, userFind);
    }

    @Test
    public void findAll() {
        List<User> studentList = userService.findAll();
        assertEquals(user, studentList.get(0));
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(2);
        userService.update(this.user);
        verify(userDao, times(1)).update(this.user);
    }

    @Test
    public void delete() {
        userService.delete(2);
        verify(userDao, times(1)).delete(2);
    }


    @Test
    public void findByEmail() {
        User findUser = userService.findByEmail("email");
        verify(userDao, times(1)).findByEmail("email");
        assertEquals(user, findUser);
    }

    @Test
    public void loginUser() {
        userService.loginUser("login", "password");
        verify(userDao, times(1)).getByLoginAndPass("login", "password");

    }
}