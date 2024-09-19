package com.emazon.api_user.infraestructure.output.adapter.securityconfig;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.application.dto.authentication.AuthenticationResponseDto;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        var jwtToken = jwtService.generateToken(userDetails.getUsername(),userDetails);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}