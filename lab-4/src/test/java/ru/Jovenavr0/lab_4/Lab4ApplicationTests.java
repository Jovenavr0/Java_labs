package ru.Jovenavr0.lab_4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Lab4ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void unAuthorized() throws Exception {
		mvc.perform(get("/api/pets/1")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(roles = "USER")
	void userAuthorizedCanGetPets() throws Exception {
		mvc.perform(get("/api/pets")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void adminCanDeletePet() throws Exception {
		mvc.perform(delete("/api/pets/1")).andExpect(status().isNoContent());
	}

}
