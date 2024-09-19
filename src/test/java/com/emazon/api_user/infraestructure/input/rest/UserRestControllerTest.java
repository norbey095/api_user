package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.util.Constans;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserHandler
            userHandler;
    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void createUser_ShouldReturnStatusCreated() throws Exception {
        ResponseSuccess responseSuccess = new ResponseSuccess(Constans.MESSAGESS_SUCCESS);
        Mockito.when(userHandler.saveUser(Mockito.any(UserRequestDto.class)))
                .thenReturn(responseSuccess);

        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}