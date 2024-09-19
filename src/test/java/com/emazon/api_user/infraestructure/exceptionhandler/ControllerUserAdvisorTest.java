package com.emazon.api_user.infraestructure.exceptionhandler;

import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.format.DateTimeParseException;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ControllerUserAdvisorTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUserHandler userHandler;

    @MockBean
    private JwtService jwtService;

    @Test
    void whenMethodArgumentNotValidException_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST_INCORRECT))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.NAME_EMPTY));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenUserAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new UserAlreadyExistsException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.MESSAGE_EMAIL));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenUserEmailInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new UserEmailInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.USER_EMAIL_CORRECT));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenPhoneNumberInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new PhoneNumberinvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.USER_PHONE_CORRECT));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenMinorInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new MinorInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.MINOR_INVALID));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenRolAuxBodegaInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new RolAuxBodegaInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.ROL_AUX_BODE_EXISTS));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenDocumentInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new DocumentInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.DOCUMENT_NUMBER_POSITIVE));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenBadCredentialsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new BadCredentialsException(Constans.INAUTHORIZATION)).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.INAUTHORIZATION));
    }

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void whenDateTimeParseException_thenReturnsConflict() throws Exception {
        DateTimeParseException dateTimeParseException = new DateTimeParseException(Constans.DATE_TIME,
                Constans.INPUT, Constans.VALUE_0);

        Mockito.doThrow(dateTimeParseException).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(Constans.MESSAGE)
                        .value(Constans.DATE_TIME));
    }

}