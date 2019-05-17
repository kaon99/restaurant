package model.dao.daoimpl;


import model.dao.daointerface.MenuDao;
import model.dao.mapper.MenuMapper;
import model.entity.Menu;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private Logger logger = Logger.getLogger(MenuDaoImpl.class);
    private Connection connection;

    public MenuDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Menu create(Menu entity) {
        try (PreparedStatement statement = connection.prepareStatement("INsert into resstaurant.menu(nameEn,nameUa, price) value (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getNameEn());
            statement.setString(1, entity.getNameUa());
            statement.setInt(2, entity.getPrice());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            logger.info("Menu do not create", e);
        }
        return entity;
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
        }
        return null;
    }


    @Override
    public List<Menu> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("select * FROM resstaurant.menu")) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList menu = new ArrayList();
            while (resultSet.next()) {
                menu.add(new MenuMapper().extractFromResultSet(resultSet));
            }
            return menu;
        } catch (SQLException e) {
            logger.info("Cannot find All", e);
        }
        return null;
    }

    @Override
    public void update(Menu entity) {
        try (PreparedStatement statement = connection.prepareStatement("update  resstaurant.menu set menu.nameEn = ?, menu.nameUa ,menu.price = ?  ")) {

            statement.setString(1, entity.getNameEn());
            statement.setString(2, entity.getNameUa());
            statement.setInt(3, entity.getPrice());
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

    @Override
    public List<Menu> findDishListById(int[] id) {
        try (PreparedStatement statement = connection.prepareStatement("select * FROM resstaurant.menu where menu_id = ?")) {

            ArrayList menu = new ArrayList();
            for (int i : id) {
                statement.setInt(1, i);
                statement.addBatch();

            }
            statement.executeBatch();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                menu.add(new MenuMapper().extractFromResultSet(resultSet));
            }
            return menu;
        } catch (SQLException e) {
            logger.info("Cannot find by id", e);
        }
        return null;

    }
}
