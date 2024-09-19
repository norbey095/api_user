package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.application.dto.authentication.AuthenticationResponseDto;
import com.emazon.api_user.application.handler.authetication.IAuthenticationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationHandler authenticationHandler;

    @Operation(summary = "Authenticacion User",
            description = "Authenticacion User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated user", content = @Content),
            @ApiResponse(responseCode = "403", description = "Incorrect login information", content = @Content),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return  ResponseEntity.ok(new AuthenticationResponseDto(authenticationHandler.authentication(request)));
    }
}