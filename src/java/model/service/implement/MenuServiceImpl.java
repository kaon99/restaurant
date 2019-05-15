package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.MenuDao;
import model.entity.Menu;
import model.service.MenuService;
import org.apache.log4j.Logger;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Override
    public void create(Menu entity) {
        try (MenuDao menuDao = daoFactory.createMenuDao()) {
            menuDao.create(entity);
            logger.info("Create menu = %d");
        }

        }

    @Override
    public Menu findById(int id) {
        try (MenuDao menuDao = daoFactory.createMenuDao()) {
            Menu menu = menuDao.findById(id);
            logger.info("Find menu by id ");
            return menu;
        }


    }

    @Override
    public List<Menu> findAll() {
        try (MenuDao menuDao = daoFactory.createMenuDao()) {
            List<Menu> menuList = menuDao.findAll();
            logger.info("Find all menus");
            return menuList;
        }


    }

    @Override
    public void update(Menu entity) {
        try (MenuDao menuDao = daoFactory.createMenuDao()) {
            menuDao.update(entity);
            logger.info("bill update %d");
        }


    }

    @Override
    public void delete(int id) {
        try (MenuDao menuDao = daoFactory.createMenuDao()) {
            menuDao.delete(id);
            logger.info("delete bill");
        }


    }
}
