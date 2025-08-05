package ru.Jovenavr0.dao;

import ru.Jovenavr0.entity.Owner;

import java.util.List;
import java.util.UUID;

public interface OwnerDao {
    Owner save(Owner entity);
    void deleteById(UUID id);
    void deleteByEntity(Owner entity);
    void deleteAll();
    Owner update(Owner entity);
    Owner getById(UUID id);
    List<Owner> getAll();
}
