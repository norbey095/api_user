package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.application.dto.authentication.AuthenticationResponseDto;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.AuthenticationService;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.util.Constans;
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
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void createUser_ShouldReturnStatusCreated() throws Exception {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto(Constans.ROL_DESCRIPTION);

        Mockito.when(authenticationService.authenticate(authenticationRequestDto))
                .thenReturn(authenticationResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_AUTHENTICATION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}