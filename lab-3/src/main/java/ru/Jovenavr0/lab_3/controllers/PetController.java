package ru.Jovenavr0.lab_3.controllers;

import java.net.URI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Jovenavr0.lab_3.dto.PetDTO;
import ru.Jovenavr0.lab_3.entity.PetColor;
import ru.Jovenavr0.lab_3.service.PetService;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@Tag(name = "Pet Controller", description = "Information about Pet controller")
class PetController {

    private final PetService petService;

    @GetMapping("/{id}")
    @Operation(summary = "Get pet by id")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    @Operation(summary = "Create new pet")
    public ResponseEntity<PetDTO> createPet(@Valid @RequestBody PetDTO petDTO) {
        PetDTO createdPet = petService.createPet(petDTO);
        return ResponseEntity.created(URI.create("/api/pets/" + createdPet.getId())).body(createdPet);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update data pet")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id, @Valid @RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.updatePet(id, petDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete pet")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get filtered and paginated pets")
    public ResponseEntity<Page<PetDTO>> getFilteredPets(@RequestParam(required = false) PetColor color,
        @RequestParam(required = false) Integer minTailLength,
        @PageableDefault(sort = "id", size = 10) Pageable pageable) {
        return ResponseEntity.ok(petService.getFilterPets(color, minTailLength, pageable));
    }

}