package ru.Jovenavr0.lab_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.Jovenavr0.lab_4.entity.Owner;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long>, JpaSpecificationExecutor<Owner> {
    Optional<Owner> findByUsername(String username);
}
