package com.emazon.api_user.application.dto;

import com.emazon.api_user.application.util.ConstantsDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserRequestDto {

    @NotBlank(message = ConstantsDto.NAME_REQUIRED)
    private String name;
    @NotBlank(message = ConstantsDto.LAST_NAME_REQUIRED)
    private String lastName;
    @NotBlank(message = ConstantsDto.DOCUMENT_REQUIRED)
    private String documentNumber;
    @NotBlank(message = ConstantsDto.CELLPHONE_REQUIRED)
    private String cellPhone;
    @NotNull(message = ConstantsDto.BIRTHDATE_REQUIRED)
    private LocalDate birthdate;
    @NotBlank(message = ConstantsDto.EMAIL_REQUIRED)
    private String email;
    @NotBlank(message = ConstantsDto.PASSWORD_REQUIRED)
    private String password;
}
