package ru.Jovenavr0.lab_3.mapping;

import org.springframework.stereotype.Component;
import ru.Jovenavr0.lab_3.dto.PetDTO;
import ru.Jovenavr0.lab_3.entity.Pet;

import java.util.stream.Collectors;

@Component
public class PetMapper {

    public PetDTO petToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setBreed(pet.getBreed());
        petDTO.setColor(pet.getColor());
        petDTO.setTailLength(pet.getTailLength());

        if (pet.getOwner() != null){
            petDTO.setOwnerId(pet.getOwner().getId());
        }

        petDTO.setFriendIds(pet.getFriends().stream().map(Pet::getId).collect(Collectors.toSet()));

        return petDTO;
    }

    public Pet petDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setBreed(petDTO.getBreed());
        pet.setColor(petDTO.getColor());
        pet.setTailLength(petDTO.getTailLength());

        return pet;
    }

    public void updatePetFromDto(PetDTO dto, Pet pet) {
        if (dto.getName() != null) {
            pet.setName(dto.getName());
        }

        if (dto.getBirthDate() != null) {
            pet.setBirthDate(dto.getBirthDate());
        }

        if (dto.getBreed() != null) {
            pet.setBreed(dto.getBreed());
        }

        if (dto.getColor() != null) {
            pet.setColor(dto.getColor());
        }
    }
}
