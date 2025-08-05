package ru.Jovenavr0.lab_3.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Jovenavr0.lab_3.dto.PetDTO;
import ru.Jovenavr0.lab_3.entity.Owner;
import ru.Jovenavr0.lab_3.entity.Pet;
import ru.Jovenavr0.lab_3.entity.PetColor;
import ru.Jovenavr0.lab_3.mapping.PetMapper;
import ru.Jovenavr0.lab_3.repository.OwnerRepository;
import ru.Jovenavr0.lab_3.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Tag(name = "Pet Service", description = "Information about Pet service")
public class PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final PetMapper petMapper;

    @Operation(summary = "Find pets by id")
    public PetDTO getPetById (Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet not found with id:" + id));
        return petMapper.petToPetDTO(pet);
    }

    @Operation(summary = "Get all pets list")
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream().map(petMapper::petToPetDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Create new pet")
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = petMapper.petDTOToPet(petDTO);
        setOwnerFromDto(pet, petDTO.getOwnerId());
        setFriendsFromDto(pet, petDTO.getFriendIds());
        return petMapper.petToPetDTO(petRepository.save(pet));
    }

    @Operation(summary = "Update pet")
    public PetDTO updatePet(Long id, PetDTO petDTO) {

        if (!id.equals(petDTO.getId())) {
            throw new IllegalArgumentException("Pet id mismatch");
        }

        Pet pet = petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet not found with id:" + id));
        petMapper.updatePetFromDto(petDTO, pet);
        setOwnerFromDto(pet, petDTO.getOwnerId());
        setFriendsFromDto(pet, petDTO.getFriendIds());

        return petMapper.petToPetDTO(petRepository.save(pet));
    }

    @Operation(summary = "Delete pet")
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public Page<PetDTO> getFilterPets(PetColor color, Integer minTailLength, Pageable pageable) {
        Specification<Pet> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (color != null) {
                predicates.add(criteriaBuilder.equal(root.get("color"), color));
            }

            if (minTailLength != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("tailLength"), minTailLength));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return petRepository.findAll(specification, pageable).map(petMapper::petToPetDTO);
    }

    private void setOwnerFromDto(Pet pet, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("Owner not found with id:" + ownerId));
        pet.setOwner(owner);
    }

    private void setFriendsFromDto(Pet pet, Set<Long> friendIds) {
        Set<Pet> friends = friendIds.stream().map(friendId -> petRepository.findById(friendId).orElseThrow(() -> new EntityNotFoundException("Friend pet not found: " + friendId))).collect(Collectors.toSet());
        pet.setFriends(friends);
    }

}
