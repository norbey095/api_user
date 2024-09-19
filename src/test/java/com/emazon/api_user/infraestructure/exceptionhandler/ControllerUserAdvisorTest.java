package com.emazon.api_user.infraestructure.exceptionhandler;

import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.user.IUserHandler;
import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.infraestructure.output.util.JwtService;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
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
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST_INCORRECT))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.NAME_EMPTY));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenUserAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new UserAlreadyExistsException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.MESSAGE_EMAIL));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenUserEmailInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new UserEmailInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.USER_EMAIL_CORRECT));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenPhoneNumberInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new PhoneNumberinvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.USER_PHONE_CORRECT));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenMinorInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new MinorInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.MINOR_INVALID));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenRolAuxBodegaInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new RolAuxBodegaInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.ROL_AUX_BODE_EXISTS));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenDocumentInvalidException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new DocumentInvalidException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.DOCUMENT_NUMBER_POSITIVE));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenBadCredentialsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new BadCredentialsException(ConstantsInfTest.INAUTHORIZATION)).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.INAUTHORIZATION));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenDateTimeParseException_thenReturnsConflict() throws Exception {
        DateTimeParseException dateTimeParseException = new DateTimeParseException(ConstantsInfTest.DATE_TIME,
                ConstantsInfTest.INPUT, ConstantsInfTest.VALUE_0);

        Mockito.doThrow(dateTimeParseException).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.DATE_TIME));
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.ADMIN})
    void whenCredentialsException_thenReturnsUnauthorized() throws Exception {
        Mockito.doThrow(new CredentialsException()).when(userHandler)
                .saveUser(Mockito.any(UserRequestDto.class), Mockito.any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfTest.MESSAGE)
                        .value(ConstantsInfTest.INCORRECT_DATA));
    }
}