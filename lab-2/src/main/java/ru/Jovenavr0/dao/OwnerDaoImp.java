package ru.Jovenavr0.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.Jovenavr0.entity.Owner;

import java.util.List;
import java.util.UUID;

public class OwnerDaoImp implements OwnerDao {
    private final SessionFactory sessionFactory;

    public OwnerDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Owner save(Owner entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;
        }
    }

    @Override
    public void deleteById(UUID id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Owner entity = session.get(Owner.class, id);
            if (entity != null) {
                session.remove(entity);
            }
            tx.commit();
        }
    }

    @Override
    public void deleteByEntity(Owner entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Owner").executeUpdate();
            tx.commit();
        }
    }

    @Override
    public Owner update(Owner entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Owner updated = session.merge(entity);
            tx.commit();
            return updated;
        }
    }

    @Override
    public Owner getById(UUID id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Owner.class, id);
        }
    }

    @Override
    public List<Owner> getAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Owner", Owner.class).getResultList();
        }
    }
}
