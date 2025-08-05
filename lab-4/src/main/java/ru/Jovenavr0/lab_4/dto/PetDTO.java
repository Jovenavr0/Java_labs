package ru.Jovenavr0.lab_4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.Jovenavr0.lab_4.entity.PetColor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Past
    private LocalDate birthDate;

    @NotBlank
    private String breed;

    @NotNull
    private PetColor color;

    @NotNull
    private Integer tailLength;

    @NotNull
    private Long ownerId;

    private Set<Long> friendIds = new HashSet<>();

}
