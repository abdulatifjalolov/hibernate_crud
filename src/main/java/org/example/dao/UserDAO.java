package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class UserDAO implements BaseDao<UserEntity> {
    private final SessionFactory sessionFactory;
    @Override
    public UserEntity getById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserEntity users = session.get(UserEntity.class, id);
        closeSession(session);
        return users;
    }

    @Override
    public void add(UserEntity user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        closeSession(session);
    }

    @Override
    public List<UserEntity> getList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserEntity> list = session.createNativeQuery("Select * from users", UserEntity.class).getResultList();
        closeSession(session);
        return list;
    }

    @Override
    public void delete(UserEntity users) {
        Session session = sessionFactory.openSession();
        session.delete(users);
        closeSession(session);
    }

    @Override
    public void closeSession(Session session) {
        BaseDao.super.closeSession(session);
    }
}
