package ru.Jovenavr0.lab_4.mapping;

import org.springframework.stereotype.Component;
import ru.Jovenavr0.lab_4.dto.OwnerDTO;
import ru.Jovenavr0.lab_4.entity.Owner;
import ru.Jovenavr0.lab_4.entity.Pet;

import java.util.stream.Collectors;

@Component
public class OwnerMapper {

    public OwnerDTO entityToDTO(Owner entity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(entity.getId());
        ownerDTO.setName(entity.getName());
        ownerDTO.setUsername(entity.getUsername());
        ownerDTO.setPassword(entity.getPassword());
        ownerDTO.setRole(entity.getRole());
        ownerDTO.setBirthDate(entity.getBirthDay());
        ownerDTO.setPetIds(entity.getPets().stream().map(Pet::getId).collect(Collectors.toList()));

        return ownerDTO;
    }

    public Owner dtoToEntity(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setRole(ownerDTO.getRole());
        owner.setName(ownerDTO.getName());
        owner.setUsername(ownerDTO.getUsername());
        owner.setPassword(ownerDTO.getPassword());
        owner.setBirthDay(ownerDTO.getBirthDate());

        return owner;
    }

    public void updateEntityFromDTO(OwnerDTO ownerDTO, Owner owner) {
        if (ownerDTO.getName() != null) {
            owner.setName(ownerDTO.getName());
        }

        if (ownerDTO.getBirthDate() != null) {
            owner.setBirthDay(ownerDTO.getBirthDate());
        }

        if (ownerDTO.getRole() != null) {
            owner.setRole(ownerDTO.getRole());
        }

        if (ownerDTO.getUsername() != null) {
            owner.setUsername(ownerDTO.getUsername());
        }

        if (ownerDTO.getPassword() != null) {
            owner.setPassword(ownerDTO.getPassword());
        }

    }

}
