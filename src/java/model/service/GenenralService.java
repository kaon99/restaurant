package model.service;

import java.util.List;

public interface GenenralService<T> {
    void create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
