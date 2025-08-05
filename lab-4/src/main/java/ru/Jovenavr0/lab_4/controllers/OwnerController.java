package ru.Jovenavr0.lab_4.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Jovenavr0.lab_4.dto.OwnerDTO;
import ru.Jovenavr0.lab_4.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@Tag(name = "Owner Controller", description = "Controller for work with owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    @Operation(summary = "Get list with all owners")
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get owners by id")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new owner")
    public ResponseEntity<OwnerDTO> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        return new ResponseEntity<>(ownerService.createOwner(ownerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update owner")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @Valid @RequestBody OwnerDTO ownerDTO) {
        return ResponseEntity.ok(ownerService.updateOwner(id, ownerDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete owner")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ownersId}/pets/{petId}")
    @Operation(summary = "Add new pet to owner")
    public ResponseEntity<OwnerDTO> addPetToOwner(@PathVariable Long ownersId, @PathVariable Long petId) {
        return ResponseEntity.ok(ownerService.addPetToOwner(ownersId, petId));
    }

}
