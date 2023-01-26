package org.example.dao;

import java.util.List;
import org.hibernate.Session;
public interface BaseDao<T> {
    T getById(int id);
    void add(T item);

    List<T> getList();
    void delete(T item);
    default void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    };
}
