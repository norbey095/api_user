package com.emazon.api_user.infraestructure.output.adapter;

import com.emazon.api_user.domain.model.UserAuth;
import com.emazon.api_user.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_user.infraestructure.output.util.JwtService;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.util.ConstantsInfraestructure;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthenticationAdapter implements IAthenticationPersistencePort {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserAuth authenticate(String email, String password) {
        Authentication authentication = validateLoginInformation(email, password);
        if (authentication == null) {
            return null;
        }
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        UserAuth user = new UserAuth();
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRol().getName());

        return user;
    }

    @Override
    public String getToken(UserAuth user) {
        return jwtService.generateToken(user.getEmail(),generateExtraClaims(user));
    }

    private Authentication validateLoginInformation(String email, String password) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> generateExtraClaims(UserAuth user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(ConstantsInfraestructure.AUTHORITIES, ConstantsInfraestructure.ROLE + user.getRole());
        return extraClaims;
    }
}