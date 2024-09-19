package com.emazon.api_user.application.dto;

import com.emazon.api_user.application.util.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserRequestDto {

    @NotBlank(message = Constants.NAME_REQUIRED)
    private String name;
    @NotBlank(message = Constants.LAST_NAME_REQUIRED)
    private String lastName;
    @NotBlank(message = Constants.DOCUMENT_REQUIRED)
    private String documentNumber;
    @NotBlank(message = Constants.CELLPHONE_REQUIRED)
    private String cellPhone;
    @NotNull(message = Constants.BIRTHDATE_REQUIRED)
    private LocalDate birthdate;
    @NotBlank(message = Constants.EMAIL_REQUIRED)
    private String email;
    @NotBlank(message = Constants.PASSWORD_REQUIRED)
    private String password;
}
