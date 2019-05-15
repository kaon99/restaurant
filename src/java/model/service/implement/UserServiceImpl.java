package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.UserDao;
import model.entity.User;
import model.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Override
    public void create(User entity) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(entity);
        }
        logger.info("Create user = %d");

    }

    @Override
    public User findById(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findById(id);
            logger.info("Find user by id");
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            List<User> users = userDao.findAll();
            logger.info("Find all users");
            return users;
        }
    }

    @Override
    public void update(User entity) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(entity);
            logger.info("User update %d");
        }
    }

    @Override
    public void delete(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            logger.info("delete user");
            userDao.delete(id);
        }

    }

    @Override
    public User loginUser(String login, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.getByLoginAndPass(login, password);
            logger.info("get Login and password");
            return user;
        }
    }

    @Override
    public User findByEmail(String email) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findByEmail(email);
            return user;
        }

    }

}

