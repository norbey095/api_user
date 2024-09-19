package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.infraestructure.util.Constans;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserHandler userHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        userRequestDto = UserRequestDto.builder()
                .name(Constans.NAME)
                .lastName(Constans.LAST_NAME)
                .documentNumber(Constans.DOCUMENT)
                .cellPhone(Constans.CELLPHONE)
                .birthdate(Constans.BIRTHDATE)
                .email(Constans.EMAIL)
                .password(Constans.PASSWORD)
                .build();
    }

    @Test
    void createUser_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(userHandler, Mockito.times(Constans.ROL_ID)).saveUser(userRequestDto);
    }

}