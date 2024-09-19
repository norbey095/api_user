package com.emazon.api_user.infraestructure.exceptionhandler;

import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.infraestructure.input.rest.UserRestController;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {UserRestController.class, ControllerUserAdvisor.class})
class ControllerUserAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserHandler userHandler;

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

}