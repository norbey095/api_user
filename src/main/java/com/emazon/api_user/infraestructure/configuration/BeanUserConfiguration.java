package com.emazon.api_user.infraestructure.configuration;

import com.emazon.api_user.domain.api.IAuthenticationServicePort;
import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.usecase.AuthenticationUseCase;
import com.emazon.api_user.domain.usecase.UserUseCase;
import com.emazon.api_user.infraestructure.output.adapter.AuthenticationAdapter;
import com.emazon.api_user.infraestructure.output.adapter.RolJpaAdapter;
import com.emazon.api_user.infraestructure.output.adapter.UserJpaAdapter;
import com.emazon.api_user.infraestructure.output.util.JwtService;
import com.emazon.api_user.infraestructure.output.mapper.RolEntityMapper;
import com.emazon.api_user.infraestructure.output.mapper.UserEntityMapper;
import com.emazon.api_user.infraestructure.output.repository.IRolRepository;
import com.emazon.api_user.infraestructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@RequiredArgsConstructor
public class BeanUserConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository,userEntityMapper);
    }

    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolJpaAdapter(rolRepository,rolEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(),rolPersistencePort());
    }

    @Bean
    public IAthenticationPersistencePort athenticationPersistencePort(){
        return new AuthenticationAdapter(authenticationManager, jwtService);
    }
    @Bean
    public IAuthenticationServicePort authenticationServicePort(IAthenticationPersistencePort athenticationPersistencePort){
        return new AuthenticationUseCase(athenticationPersistencePort);
    }
}
