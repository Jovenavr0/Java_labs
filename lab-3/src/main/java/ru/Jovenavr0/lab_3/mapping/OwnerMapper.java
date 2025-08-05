package ru.Jovenavr0.lab_3.mapping;

import org.springframework.stereotype.Component;
import ru.Jovenavr0.lab_3.dto.OwnerDTO;
import ru.Jovenavr0.lab_3.entity.Owner;
import ru.Jovenavr0.lab_3.entity.Pet;

import java.util.stream.Collectors;

@Component
public class OwnerMapper {

    public OwnerDTO entityToDTO(Owner entity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(entity.getId());
        ownerDTO.setName(entity.getName());
        ownerDTO.setBirthDate(entity.getBirthDay());
        ownerDTO.setPetIds(entity.getPets().stream().map(Pet::getId).collect(Collectors.toList()));

        return ownerDTO;
    }

    public Owner dtoToEntity(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setName(ownerDTO.getName());
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
    }

}
