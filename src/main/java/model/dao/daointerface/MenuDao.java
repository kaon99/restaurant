package model.dao.daointerface;

import model.entity.Menu;

import java.util.List;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */


public interface MenuDao extends GenericDao<Menu> {
    /**
     * @return  list where id equals
     * @param id* */
    List<Menu> findDishListById(int [] id);
}
