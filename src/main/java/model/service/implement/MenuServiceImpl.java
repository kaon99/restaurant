package model.service.implement;

import model.dao.daoImpl.DaoFactory;
import model.dao.daoInterface.MenuDao;
import model.entity.Menu;
import model.service.MenuService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuServiceImpl implements MenuService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(MenuServiceImpl.class);
    MenuDao menuDao = daoFactory.createMenuDao();


    @Override
    public Menu create(Menu entity) {
            logger.info("Create menu ");
            return menuDao.create(entity);
    }

    @Override
    public Menu findById(int id) {
            Menu menu = menuDao.findById(id);
            logger.info("Find menu by id ");
            return menu;
    }

    @Override
    public List<Menu> findAll() {
            List<Menu> menuList = menuDao.findAll();
            logger.info("Find all menus");
            return menuList;
    }

    @Override
    public void update(Menu entity) {
            menuDao.update(entity);
            logger.info("bill update ");
    }

    @Override
    public void delete(int id) {
            menuDao.delete(id);
            logger.info("delete bill");
    }

    @Override
    public List<Menu> chooseDishes(String[] dishList, List<Menu> allDish) {
        int[] array = Arrays.stream(dishList).mapToInt(Integer::parseInt).toArray();
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : allDish) {
            for (int i : array) {
                if (menu.getId().equals(i)) {
                    menus.add(menu);
                } } }
        logger.info("set dish");
        return menus;
    }
}
