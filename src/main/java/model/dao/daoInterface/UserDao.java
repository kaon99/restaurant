package model.dao.daoInterface;

/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

import model.entity.User;

public interface UserDao extends GenericDao<User> {
    /**
     * @return entity
     * @param login
     * @param password
     * */
    User getByLoginAndPass(String login, String password);
    /** @return entity
     * @param email
     * */
    User findByEmail(String email);


}
