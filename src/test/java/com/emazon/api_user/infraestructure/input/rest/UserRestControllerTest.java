package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
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
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserHandler
            userHandler;
    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void createUserAux_ShouldReturnStatusCreated() throws Exception {
        ResponseSuccess responseSuccess = new ResponseSuccess(ConstantsInfTest.MESSAGESS_SUCCESS);
        Mockito.when(userHandler.saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class)))
                .thenReturn(responseSuccess);

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void createUserClient_ShouldReturnStatusCreated() throws Exception {
        ResponseSuccess responseSuccess = new ResponseSuccess(ConstantsInfTest.MESSAGESS_SUCCESS);
        Mockito.when(userHandler.saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class)))
                .thenReturn(responseSuccess);

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_CLIENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}