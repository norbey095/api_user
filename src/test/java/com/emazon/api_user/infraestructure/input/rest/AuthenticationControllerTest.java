package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.application.handler.authetication.AuthenticationHandler;
import com.emazon.api_user.infraestructure.output.util.JwtService;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationHandler authenticationHandler;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void createUser_ShouldReturnStatusCreated() throws Exception {
        AuthenticationRequestDto authenticationRequest = new AuthenticationRequestDto();

        Mockito.when(authenticationHandler.authentication(authenticationRequest))
                .thenReturn(ConstantsInfTest.JWTTOKEN);

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_AUTHENTICATION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}