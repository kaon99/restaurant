package model.dao.daointerface;

import java.util.List;
/**
 * GeneralDao
 * @author Yaroslav
 * @version 1.0
 *
 * */

public interface GenericDao<T> extends AutoCloseable {
    /**
     * method that create <T/>
     * @param entity
     * @return entity
     */
    T create(T entity);
    /**
     * method that gets <T/> by id
     * @param id
     * @return instance of <T/>
     */
    T findById(int id);

    /**
     * method that gets list of <T/>
     * @return list of <T/>s
     */
    List<T> findAll();
    /**
     * method that updates <T/>
     * @param entity
     * @return instance of <T/>
     */
    void update(T entity);
    /**
     * method that deletes <T/>*
     * @param id
     */
    void delete(int id);
    /**
     * back connection to connection pool
     * */
    void close();
}
