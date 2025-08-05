package ru.Jovenavr0;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.Jovenavr0.dao.OwnerDaoImp;
import ru.Jovenavr0.entity.Owner;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Transaction transaction;

    @Mock
    private Session session;

    private OwnerDaoImp ownerDaoImp;

    private final UUID testId = UUID.randomUUID();
    private final Owner testOwner = new Owner();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerDaoImp = new OwnerDaoImp(sessionFactory);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        testOwner.setId(testId);
    }

    @Test
    void saveOwner() {
        when(session.save(any(Owner.class))).thenReturn(testId);

        Owner result = ownerDaoImp.save(testOwner);

        assertNotNull(result);
        assertEquals(testId, result.getId());
        verify(session).persist(testOwner);
        verify(sessionFactory).openSession();
    }

    @Test
    void getById() {
        when(session.get(Owner.class, testId)).thenReturn(testOwner);

        Owner result = ownerDaoImp.getById(testId);

        assertEquals(testOwner, result);
        verify(session).get(Owner.class, testId);
    }

    @Test
    void getAll() {
        org.hibernate.query.Query<Owner> queryMock = mock(org.hibernate.query.Query.class);
        when(session.createQuery("FROM Owner", Owner.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(List.of(testOwner));

        List<Owner> result = ownerDaoImp.getAll();

        assertEquals(1, result.size());
        assertEquals(testOwner, result.get(0));

        verify(session, times(1)).createQuery("FROM Owner", Owner.class);
        verify(queryMock, times(1)).getResultList();
    }

    @Test
    void update() {
        when(session.merge(any(Owner.class))).thenReturn(testOwner);

        Owner result = ownerDaoImp.update(testOwner);

        assertEquals(testOwner, result);
        verify(session).merge(testOwner);
    }

    @Test
    void deleteById() {
        when(session.get(Owner.class, testId)).thenReturn(testOwner);

        ownerDaoImp.deleteById(testId);

        verify(session).remove(testOwner);
        verify(session).get(Owner.class, testId);
    }

    @Test
    void deleteByEntity() {
        when(session.get(Owner.class, testId)).thenReturn(testOwner);

        ownerDaoImp.deleteByEntity(testOwner);

        verify(session).remove(testOwner);
    }

    @Test
    void deleteAll() {
        when(session.createMutationQuery("DELETE FROM Owner"))
                .thenReturn(mock(org.hibernate.query.Query.class));

        ownerDaoImp.deleteAll();

        verify(session).createMutationQuery("DELETE FROM Owner");
    }
}