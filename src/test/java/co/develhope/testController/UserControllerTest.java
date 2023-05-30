package co.develhope.testController;

import co.develhope.testController.controller.UserController;
import co.develhope.testController.entities.User;
import co.develhope.testController.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private UserController userController;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

	@Test
	public void create() throws Exception {
		User user = new User();
		user.setName("Lucrezia");
		user.setSurname("Rumore");
		user.setActive(true);
		user.setAge(25);

		String userJson = objectMapper.writeValueAsString(user);
		this.mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetAllUsers() throws Exception {
			this.mockMvc.perform(get("/users"))
					.andDo(print())
					.andExpect(status().isOk());
	}



@Test
public void testGetUserById() throws Exception {
		this.mockMvc.perform(get("/users/{id}"))
				.andDo(print())
				.andExpect(status().isOk());

}

	@Test
	public void update() throws Exception {
		create();
		this.mockMvc.perform(put("/users{id}")
						.param("eta","26"))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void deleteUser() throws Exception {
		create();
		this.mockMvc.perform(delete("/users/{id}"))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

}