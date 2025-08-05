package ru.Jovenavr0.service;

import ru.Jovenavr0.dao.OwnerDao;
import ru.Jovenavr0.entity.Owner;

import java.util.List;
import java.util.UUID;

public class OwnerService implements IOwnerService {

    private final OwnerDao ownerDao;

    public OwnerService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public Owner saveOwner(Owner owner) {
        if (owner.getName() == null || owner.getName().isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be blank or empty");
        }
        return ownerDao.save(owner);
    }

    @Override
    public void deleteOwner(Owner owner) {
        Owner existingOwner = ownerDao.getById(owner.getId());
        if (existingOwner == null) {
            throw new IllegalArgumentException("Owner not found");
        }
        ownerDao.deleteByEntity(existingOwner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        Owner existingOwner = ownerDao.getById(owner.getId());
        if (existingOwner == null) {
            throw new IllegalArgumentException("Owner not found");
        }
        return ownerDao.update(existingOwner);
    }

    @Override
    public Owner getById(UUID id) {
        return ownerDao.getById(id);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDao.getAll();
    }
}
