package model.dao.daoImpl;


import model.dao.connectionPool.ConnectionPool;
import model.dao.daoInterface.MenuDao;
import model.dao.mapper.MenuMapper;
import model.dao.queriesManager.QueriesResourceManager;
import model.entity.Menu;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private Logger logger = Logger.getLogger(MenuDaoImpl.class);



    @Override
    public Menu create(Menu entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("menu.create"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getNameEn());
            statement.setString(2, entity.getNameUa());
            statement.setInt(3, entity.getPrice());
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
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("menu.find.by.id"))) {
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
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("menu.find.all"))) {
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
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("menu.update"))) {

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
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("menu.delete"))) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException var3) {
            logger.info("Menu don`t delete ");
        }

    }



    @Override
    public List<Menu> findDishListById(int[] id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("menu.find.dish.by.id"))) {

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
