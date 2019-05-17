package model.dao.mapper;

import model.entity.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MenuMapper implements  ObjectMapper<Menu>{
    @Override
    public Menu extractFromResultSet(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setId(rs.getInt("menu_id"));
        menu.setNameEn(rs.getString("nameEn"));
        menu.setNameUa(rs.getString("nameUa"));
        menu.setPrice(rs.getInt("price"));
        return menu;
    }

    @Override
    public Menu makeUnique(Map<Integer, Menu> cache, Menu menu) {
        cache.putIfAbsent(menu.getId(),menu);
        return cache.get(menu.getId());
    }
}
