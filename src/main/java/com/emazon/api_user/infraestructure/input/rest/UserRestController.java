package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.infraestructure.util.ConstantsInfraestructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new aux",
            description = "Save an aux")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created", content = @Content),
            @ApiResponse(responseCode = "409", description = "user already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PreAuthorize(ConstantsInfraestructure.HAS_ADMIN)
    @PostMapping("/registryAux")
    public ResponseEntity<ResponseSuccess> createUserAux(@Valid @RequestBody UserRequestDto userRequestDto){
        ResponseSuccess responseSuccess = userHandler.saveUser(userRequestDto, ConstantsInfraestructure.ROLE_AUX_WAREHOUSE);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }

    @Operation(summary = "Add a new client",
            description = "Save an client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created", content = @Content),
            @ApiResponse(responseCode = "409", description = "user already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PreAuthorize(ConstantsInfraestructure.HAS_ADMIN)
    @PostMapping("/registryClient")
    public ResponseEntity<ResponseSuccess> createUserClient(@Valid @RequestBody UserRequestDto userRequestDto){
        ResponseSuccess responseSuccess = userHandler.saveUser(userRequestDto, ConstantsInfraestructure.ROLE_CLIENT);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }
}
