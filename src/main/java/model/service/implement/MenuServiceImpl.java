package model.service.implement;

import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.MenuDao;
import model.entity.Menu;
import model.service.MenuService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuServiceImpl implements MenuService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Override
    public Menu create(Menu entity) {
        try (MenuDao menuDao = daoFactory.createMenuDao()) {
            logger.info("Create menu = %d");
            return menuDao.create(entity);

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

    @Override
    public List<Menu> chooseDishes(String[] dishList, List<Menu> allDish) {
        int[] array = Arrays.stream(dishList).mapToInt(Integer::parseInt).toArray();

        List<Menu> menus = new ArrayList<>();
        for (Menu menu : allDish) {
            for (int i : array) {
                if (menu.getId().equals(i)) {
                    menus.add(menu);
                }
            }

        }
        return menus;


    }
}
