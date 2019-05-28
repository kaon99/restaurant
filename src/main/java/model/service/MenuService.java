package model.service;

import model.entity.Menu;

import java.util.List;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */


public interface MenuService extends GenenralService<Menu> {
    List<Menu> chooseDishes(String [] dishList, List<Menu> allDish);
}
