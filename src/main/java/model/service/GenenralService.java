package model.service;

import java.util.List;

public interface GenenralService<T> {
    T create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
