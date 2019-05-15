package model.dao.daointerface;


import model.entity.User;

public interface UserDao extends GenericDao<User> {
    User getByLoginAndPass(String login, String password) ;
    User findByEmail(String email);


}
