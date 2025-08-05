package ru.Jovenavr0.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.Jovenavr0.entity.Pet;

import java.util.List;
import java.util.UUID;

public class PetDaoImp implements PetDao {

    private final SessionFactory sessionFactory;

    public PetDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pet save(Pet entity) {
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
            Pet entity = session.get(Pet.class, id);
            if (entity != null) {
                session.remove(entity);
            }
            tx.commit();
        }
    }

    @Override
    public void deleteByEntity(Pet entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Pet").executeUpdate();
            tx.commit();
        }
    }

    @Override
    public Pet update(Pet entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Pet updated = session.merge(entity);
            tx.commit();
            return updated;
        }
    }

    @Override
    public Pet getById(UUID id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Pet.class, id);
        }
    }

    @Override
    public List<Pet> getAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Pet", Pet.class).getResultList();
        }
    }

    @Override
    public void addFriend(UUID id1, UUID id2) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Pet entity1 = session.get(Pet.class, id1);
            Pet entity2 = session.get(Pet.class, id2);

            entity1.addFriend(entity2);
            session.merge(entity1);
            session.merge(entity2);

            tx.commit();
        }
    }

}
