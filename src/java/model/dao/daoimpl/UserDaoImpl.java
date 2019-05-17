package model.dao.daoimpl;


import model.dao.daointerface.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    private Logger logger = Logger.getLogger(UserDaoImpl.class);
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User entity) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO resstaurant.user(name, password, role, email) VALUE (?,?,?,?)" ,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole());
            statement.setString(4, entity.getEmail());
            statement.executeUpdate();
ResultSet resultSet = statement.getGeneratedKeys();
if(resultSet.next()){
entity.setId(resultSet.getInt(1));
}
        } catch (SQLException e) {
            logger.info("Do not create user", e);
        }
        return entity;
    }

    @Override
    public User findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM resstaurant.user where user_id = ?")) {

            User user =  null;
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                UserMapper userMapper  = new UserMapper();
                user = userMapper.extractFromResultSet(resultSet);

            }
            return user;
        } catch (SQLException e) {
            logger.info("Do not find user",e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try(PreparedStatement statement = connection.prepareStatement("select * from resstaurant.user ")) {
            ResultSet resultSet = statement.executeQuery();
            List users = new ArrayList();
            while (resultSet.next()){
                users.add(new UserMapper().extractFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            logger.info("Cannot find All",e);
        }
        return null;
    }

    @Override
    public void update(User entity) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE resstaurant.user SET  name=?, password = ?, role = ?, email = ? where user_id = ? ")) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole());
            statement.setString(4, entity.getEmail());
            statement.setInt(5,entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Cannot update user",e);
        }
    }


    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM resstaurant.user where user_id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("User don`t delete ",e);
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
    public User getByLoginAndPass(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM resstaurant.user where user.email = ? AND user.password = ?")) {

            User user =  null;
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                UserMapper userMapper  = new UserMapper();
                user = userMapper.extractFromResultSet(resultSet);

            }
            return user;
        } catch (SQLException e) {
            logger.info("Do not find user",e);
        }

        return null;
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM resstaurant.user where user.email = ?")) {

            User user =  null;
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                UserMapper userMapper  = new UserMapper();
                user = userMapper.extractFromResultSet(resultSet);

            }
            return user;
        } catch (SQLException e) {
            logger.info("Do not find user",e);
        }
        return null;
    }
}
