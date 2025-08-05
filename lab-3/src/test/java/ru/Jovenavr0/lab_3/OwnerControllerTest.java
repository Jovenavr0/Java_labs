package ru.Jovenavr0.lab_3;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.Jovenavr0.lab_3.controllers.OwnerController;
import ru.Jovenavr0.lab_3.dto.OwnerDTO;
import ru.Jovenavr0.lab_3.service.OwnerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
class OwnerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    OwnerService ownerService;

    @Test
    void getAllOwnersShouldReturnAllOwners() throws Exception {
        Mockito.when(this.ownerService.getAllOwners()).thenReturn(getOwnerDTOs());

        mvc.perform(get("/api/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void findOneShouldReturnValidBook() throws Exception {
        Mockito.when(this.ownerService.getOwnerById(1L)).thenReturn(getOwnerDTOs().getFirst());

        mvc.perform(get("/api/owners/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.birthDate").value(LocalDate.EPOCH.toString()))
                .andExpect(jsonPath("$.petIds").value(new ArrayList<>()));
    }


    private List<OwnerDTO> getOwnerDTOs() {
        OwnerDTO one = new OwnerDTO(1L, "John", LocalDate.EPOCH, new ArrayList<>());
        OwnerDTO two = new OwnerDTO(2L, "Den", LocalDate.EPOCH, new ArrayList<>());
        return List.of(one, two);
    }

}