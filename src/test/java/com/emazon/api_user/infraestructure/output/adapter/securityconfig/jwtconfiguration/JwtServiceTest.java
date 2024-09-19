package com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration;

import com.emazon.api_user.infraestructure.util.Constans;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String base64SecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        jwtService = new JwtService(base64SecretKey);
    }

    @Test
    void testGenerateToken() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constans.ADMIN));

        Mockito.doReturn(authorities).when(userDetails)
                .getAuthorities();

        String email = Constans.EMAIL_EJEM;
        String token = jwtService.generateToken(email, userDetails);

        assertTrue(token != null && !token.isEmpty());
    }

    @Test
    void testExtractUsername() {
        String username = Constans.EMAIL_EJEM;
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        ((long) Constans.VALUE_100 * Constans.VALUE_60 * Constans.VALUE_60)))
                .signWith(secretKey)
                .compact();

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(username, extractedUsername, Constans.NAME_INVALID);
    }

    @Test
    void testIsTokenValid_ValidToken_ReturnsTrue() {
        String username = Constans.EMAIL_EJEM;
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        ((long) Constans.VALUE_100 * Constans.VALUE_60 * Constans.VALUE_60)))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        Mockito.when(userDetails.getUsername()).thenReturn(username);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid, Constans.TOKEN_VALID);
    }

    @Test
    void testIsTokenValid_InvalidToken_ReturnsFalse() {
        String username = Constans.EMAIL_EJEM;
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        ((long) Constans.VALUE_100 * Constans.VALUE_60 * Constans.VALUE_60)))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        Mockito.when(userDetails.getUsername()).thenReturn(Constans.EMAIL);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertFalse(isValid, Constans.TOKEN_INVALID);
    }
}