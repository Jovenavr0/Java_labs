package ru.Jovenavr0.service;

import ru.Jovenavr0.entity.Pet;

import java.util.List;
import java.util.UUID;

public interface IPetService {
    Pet savePet(Pet pet);

    void deletePet(Pet pet);

    Pet updatePet(Pet pet);

    Pet getById(UUID id);

    List<Pet> getAllPets();

    void addFriend(UUID id1, UUID id2);
}
