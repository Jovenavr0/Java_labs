package ru.Jovenavr0.service;

import ru.Jovenavr0.entity.Owner;

import java.util.List;
import java.util.UUID;

public interface IOwnerService {
    Owner saveOwner(Owner owner);

    void deleteOwner(Owner owner);

    Owner updateOwner(Owner pet);

    Owner getById(UUID id);

    List<Owner> getAllOwners();
}
