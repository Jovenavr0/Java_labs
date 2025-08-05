package ru.Jovenavr0.service;

import ru.Jovenavr0.dao.PetDao;
import ru.Jovenavr0.entity.Pet;

import java.util.List;
import java.util.UUID;

public class PetService implements IPetService {

    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    @Override
    public Pet savePet(Pet pet) {
        if (pet.getName() == null || pet.getName().isBlank()) {
            throw new IllegalArgumentException("Pet name cannot be null or empty");
        }
        return petDao.save(pet);
    }

    @Override
    public void deletePet(Pet pet) {
        Pet existingPet = petDao.getById(pet.getId());
        if (existingPet == null) {
            throw new IllegalArgumentException("Pet not found");
        }
        petDao.deleteByEntity(existingPet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        Pet existingPet = petDao.getById(pet.getId());
        if (existingPet == null) {
            throw new IllegalArgumentException("Pet not found");
        }
        return petDao.update(existingPet);
    }

    @Override
    public Pet getById(UUID id) {
        return petDao.getById(id);
    }

    @Override
    public List<Pet> getAllPets() {
        return petDao.getAll();
    }

    @Override
    public void addFriend(UUID id1, UUID id2) {
        if (id1 == id2){
            throw new IllegalArgumentException("Friends can't be the same");
        }

        Pet pet1 = petDao.getById(id1);
        Pet pet2 = petDao.getById(id2);

        if (pet1 == null || pet2 == null) {
            throw new IllegalArgumentException("Pet not found");
        }

        petDao.addFriend(id1, id2);

    }
}
