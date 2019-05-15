package model.dao.daoimpl;


import model.dao.daointerface.MenuDao;
import model.dao.mapper.MenuMapper;
import model.entity.Menu;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private Logger logger = Logger.getLogger(MenuDaoImpl.class);
    private Connection connection;

    public MenuDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Menu entity) {
        try (PreparedStatement statement = connection.prepareStatement("INsert into resstaurant.menu(name, price) value (?,?)")) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Menu do not create", e);
        }
    }

    @Override
    public Menu findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM resstaurant.menu where menu_id = ?")) {
            Menu menu = null;
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MenuMapper menuMapper = new MenuMapper();
                menu = menuMapper.extractFromResultSet(resultSet);
            }
            return menu;
        } catch (SQLException e) {
            logger.info("Menu do not find by Id", e);
        }return null;
    }




    @Override
    public List<Menu> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("select * FROM resstaurant.menu")){
            ResultSet resultSet = statement.executeQuery();
            ArrayList menu = new ArrayList();
            while (resultSet.next()){
                menu.add(new MenuMapper().extractFromResultSet(resultSet));
            }
            return menu;
        } catch (SQLException e) {
            logger.info("Cannot find All",e);
        }
        return null;
}

    @Override
    public void update(Menu entity) {
        try (PreparedStatement statement = connection.prepareStatement("update  resstaurant.menu set menu.name = ? ,menu.price = ?  ")) {

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.info("Cannot update ", e);
        }
    }
        @Override
    public void delete(int id) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM resstaurant.menu where menu_id = ? ")) {
                statement.setInt(1, id);
                statement.executeUpdate();

            } catch (SQLException var3) {
                logger.info("Menu don`t delete ");
            }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Close ", e);
        }
    }
}
