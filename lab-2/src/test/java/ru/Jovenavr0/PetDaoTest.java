package ru.Jovenavr0;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.Jovenavr0.dao.PetDaoImp;
import ru.Jovenavr0.entity.Owner;
import ru.Jovenavr0.entity.Pet;
import ru.Jovenavr0.entity.PetColor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PetDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private org.hibernate.query.Query<Pet> query;

    private PetDaoImp petDao;

    private final UUID testId = UUID.randomUUID();
    private final Owner testOwner = new Owner();

    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        petDao = new PetDaoImp(sessionFactory);
        pet = new Pet();
        pet.setId(testId);
        pet.SetColor(PetColor.brown);
        pet.SetName("Brown");
        pet.SetBirthDate(LocalDate.now());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void savePet() {
        when(session.save(any(Pet.class))).thenReturn(testId);

        Pet result = petDao.save(pet);

        assertNotNull(result);
        assertEquals(testId, result.getId());
        verify(session).persist(pet);
        verify(sessionFactory).openSession();
    }

    @Test
    void getById() {
        when(session.get(Pet.class, testId)).thenReturn(pet);

        Pet result = petDao.getById(testId);

        assertEquals(pet, result);
        verify(session).get(Pet.class, testId);
    }

    @Test
    void getAll() {
        when(session.createQuery("FROM Pet", Pet.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(pet));

        List<Pet> result = petDao.getAll();

        assertEquals(1, result.size());
        assertEquals(pet, result.get(0));
        verify(session).createQuery("FROM Pet", Pet.class);
    }

    @Test
    void update_() {
        when(session.merge(any(Pet.class))).thenReturn(pet);

        Pet result = petDao.update(pet);

        assertEquals(pet, result);
        verify(session).merge(pet);
        verify(transaction).commit();
    }

    @Test
    void deleteById() {
        when(session.get(Pet.class, testId)).thenReturn(pet);

        petDao.deleteById(testId);

        verify(session).remove(pet);
        verify(transaction).commit();
    }

    @Test
    void deleteAll() {
        when(session.createMutationQuery("DELETE FROM Pet"))
                .thenReturn(mock(org.hibernate.query.Query.class));

        petDao.deleteAll();

        verify(session).createMutationQuery("DELETE FROM Pet");
        verify(transaction).commit();
    }

    @Test
    void addFriend() {
        UUID friendId = UUID.randomUUID();
        Pet friend = new Pet();
        friend.setId(friendId);

        when(session.get(Pet.class, testId)).thenReturn(pet);
        when(session.get(Pet.class, friendId)).thenReturn(friend);

        petDao.addFriend(testId, friendId);

        assertTrue(pet.getFriends().contains(friend));
        assertTrue(friend.getFriends().contains(pet));
        verify(session).merge(pet);
        verify(session).merge(friend);
        verify(transaction, times(1)).commit();
    }

}
