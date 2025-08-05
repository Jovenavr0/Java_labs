package ru.Jovenavr0.lab_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Jovenavr0.lab_3.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
