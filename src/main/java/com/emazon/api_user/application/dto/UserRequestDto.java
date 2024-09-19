package com.emazon.api_user.application.dto;

import com.emazon.api_user.application.util.ConstantsApplication;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserRequestDto {

    @NotBlank(message = ConstantsApplication.NAME_REQUIRED)
    private String name;
    @NotBlank(message = ConstantsApplication.LAST_NAME_REQUIRED)
    private String lastName;
    @NotBlank(message = ConstantsApplication.DOCUMENT_REQUIRED)
    private String documentNumber;
    @NotBlank(message = ConstantsApplication.CELLPHONE_REQUIRED)
    private String cellPhone;
    @NotNull(message = ConstantsApplication.BIRTHDATE_REQUIRED)
    private LocalDate birthdate;
    @NotBlank(message = ConstantsApplication.EMAIL_REQUIRED)
    private String email;
    @NotBlank(message = ConstantsApplication.PASSWORD_REQUIRED)
    private String password;
}
