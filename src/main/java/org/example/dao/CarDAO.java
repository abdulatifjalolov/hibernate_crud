package org.example.dao;
import lombok.RequiredArgsConstructor;
import org.example.entity.CarEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
@RequiredArgsConstructor
public class CarDAO implements BaseDao<CarEntity>{
    private final SessionFactory sessionFactory;

    @Override
    public CarEntity getById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CarEntity car = session.get(CarEntity.class, id);
        closeSession(session);
        return car;
    }

    @Override
    public void add(CarEntity car) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(car);
        closeSession(session);
    }

    @Override
    public List<CarEntity> getList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<CarEntity> list = session.createNativeQuery("Select * from cars", CarEntity.class).getResultList();
        closeSession(session);
        return list;
    }

    @Override
    public void delete(CarEntity car) {
        Session session = sessionFactory.openSession();
        session.delete(car);
        closeSession(session);
    }

    @Override
    public void closeSession(Session session) {
        BaseDao.super.closeSession(session);
    }
}
