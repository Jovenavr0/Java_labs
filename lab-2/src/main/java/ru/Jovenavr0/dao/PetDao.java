package ru.Jovenavr0.dao;

import ru.Jovenavr0.entity.Pet;

import java.util.List;
import java.util.UUID;

public interface PetDao {
    Pet save(Pet entity);
    void deleteById(UUID id);
    void deleteByEntity(Pet entity);
    void deleteAll();
    Pet update(Pet entity);
    Pet getById(UUID id);
    List<Pet> getAll();

    void addFriend(UUID id1, UUID id2);
}
