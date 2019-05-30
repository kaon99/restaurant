package model.service.implement;

import model.dao.daoImpl.DaoFactory;
import model.dao.daoInterface.UserDao;
import model.entity.User;
import model.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private Logger logger = Logger.getLogger(UserServiceImpl.class);
    UserDao userDao = daoFactory.createUserDao();


    @Override
    public User create(User entity) {
        return userDao.create(entity);

    }

    @Override
    public User findById(int id) {
        User user = userDao.findById(id);
        logger.info("Find user by id");
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        logger.info("Find all users");
        return users;
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
        logger.info("User update ");
    }

    @Override
    public void delete(int id) {
        logger.info("delete user");
        userDao.delete(id);
    }

    @Override
    public User loginUser(String login, String password) {
        User user = userDao.getByLoginAndPass(login, password);
        logger.info("get Login and password");
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return user;
    }
}

