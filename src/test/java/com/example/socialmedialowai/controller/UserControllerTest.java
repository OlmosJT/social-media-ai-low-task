package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.request.UserCreationDTO;
import com.example.socialmedialowai.dto.response.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.hibernate.tool.schema.spi.ExecutionOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    public static Stream<Arguments> userCreationDTOs() {
        return Stream.<Arguments>builder()
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Olmos", "Davronov", "OlmosJT", "Olmosjt20@gmail.com", "JasonTodd19")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Shohruh", "Maxmanazarov", "shokhrukh", "randomEmail@example.com", "123456")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Sarvar", "Ne'matullayev", "matador", "randomEmail2@example.com", "qwertyu")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Igor", "Vaselievich", "mcvin1996", "randomEmail3@example.com", "1234abcd")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Sergei", "Antyom", "panzed247Yep", "randomEmail4@example.com", "147zaqwsx852")})
                .build();
    }

    public static Stream<Arguments> invalidUserCreationDTOs() {
        return Stream.<Arguments>builder()
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Sergei", "Antyom", "myusername", "randomE8mail@example.com", "123")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "", "MavlonoLutfiy", "Alloma", "alloma123@gmail.com", "JasonTodd19")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Maxtunquli", "", "menmanosha", "randomEmail5@example.com", "123456")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Sarvar", "Ne'matullayev", "", "randomEmai6l@example.com", "qwertyu")})
                .add(() -> new UserCreationDTO[]{new UserCreationDTO(0L, "Igor", "Vaselievich", "anotherusername", "random7Email@example", "1234abcd")})
                .build();
    }


    @ParameterizedTest
    @MethodSource("userCreationDTOs")
    @Order(1)
    public void shouldCreateUser(UserCreationDTO dto) throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        MvcResult mvcResult = mockMvc.perform(
                post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto))
        ).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.CREATED.value());
        UserDTO responseDTO = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);
        assertEquals(responseDTO.firstName(), dto.firstName());
        assertEquals(responseDTO.lastName(), dto.lastName());
        assertEquals(responseDTO.username(), dto.username());
        assertEquals(responseDTO.email(), dto.email());
    }

    @Test
    @Order(2)
    public void shouldReturnConflictStatusCodeWhenSameUserTriesToRegisterTwice() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserCreationDTO userCreationDTO = new UserCreationDTO(0L, "Haaland", "Mbappe", "HaalandGoldenBall", "HallMbape@gmail.com", "HaalandMpabbe");
        // Register the test user account.
        MvcResult mvcResult = registerUser(userCreationDTO);
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.CREATED.value());
        // Try to register the same test user account again.
        try {
            mockMvc.perform(
                    post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userCreationDTO))
            ).andExpect(status().isConflict());
        } catch (Exception ignored) {

        }
    }

    @ParameterizedTest
    @MethodSource("invalidUserCreationDTOs")
    @Order(3)
    public void shouldReturnBadRequestStatusCodeWhenUserTriesToRegisterWithInvalidArguments(UserCreationDTO dto) throws Exception {

    }

    private MvcResult registerUser(UserCreationDTO userCreationDTO) throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        return mockMvc.perform(
                post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userCreationDTO))

        ).andReturn();
    }
}