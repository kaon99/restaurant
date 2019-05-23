package model.dao.daointerface;

import model.entity.Menu;

import java.util.List;


public interface MenuDao extends GenericDao<Menu> {
    List<Menu> findDishListById(int [] id);
}
