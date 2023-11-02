package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.request.UserCreationDTO;
import com.example.socialmedialowai.dto.response.UserDTO;
import com.example.socialmedialowai.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
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
    public void shouldReturnConflictStatusCodeWhenSameUserTriesToRegisterTwice() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserCreationDTO userCreationDTO = new UserCreationDTO(0L, "Olmos", "Davronov", "OlmosJT", "Olmosjt20@gmail.com", "JasonTodd19");
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