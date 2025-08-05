package ru.Jovenavr0.lab_4.service;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Jovenavr0.lab_4.dto.OwnerDTO;
import ru.Jovenavr0.lab_4.entity.Owner;
import ru.Jovenavr0.lab_4.entity.Pet;
import ru.Jovenavr0.lab_4.mapping.OwnerMapper;
import ru.Jovenavr0.lab_4.repository.OwnerRepository;
import ru.Jovenavr0.lab_4.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PetRepository petRepository;
    private final OwnerMapper ownerMapper;

    @Operation(summary = "Get all owners list")
    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream().map(ownerMapper::entityToDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Find owners by id")
    public OwnerDTO getOwnerById (Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Owner not found with id:" + id));
        return ownerMapper.entityToDTO(owner);
    }

    @Transactional
    @Operation(summary = "Create new owner")
    public OwnerDTO createOwner(@Valid @NotNull OwnerDTO ownerDTO) {
        ownerDTO.setPassword(passwordEncoder.encode(ownerDTO.getPassword()));
        Owner owner = ownerMapper.dtoToEntity(ownerDTO);
        return ownerMapper.entityToDTO(ownerRepository.save(owner));
    }

    @Operation(summary = "Update owner")
    public OwnerDTO updateOwner (Long id, OwnerDTO ownerDTO) {

        if (!id.equals(ownerDTO.getId())) {
            throw new IllegalArgumentException("IDs do not match");
        }

        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Owner not found with id:" + id));
        ownerMapper.updateEntityFromDTO(ownerDTO, owner);
        return ownerMapper.entityToDTO(owner);
    }

    @Operation(summary = "Delete owner")
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    @Operation(summary = "Add pet to owner")
    public OwnerDTO addPetToOwner(Long ownerId, Long petId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("Owner not found with id:" + ownerId));
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new EntityNotFoundException("Pet not found with id:" + petId));
        owner.getPets().add(pet);
        pet.setOwner(owner);
        ownerRepository.save(owner);
        petRepository.save(pet);

        return ownerMapper.entityToDTO(owner);
    }

}
