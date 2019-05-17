package model.service;

import model.entity.Menu;

import java.util.List;

public interface MenuService extends GenenralService<Menu> {
    List<Menu> chooseDishes(String [] dishList, List<Menu> allDish);
}
