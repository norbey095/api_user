package com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration;

import com.emazon.api_user.infraestructure.util.Constans;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

class JwtauthenticationFilterTest {

    @InjectMocks
    private TestableJwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternal_NoAuthHeader() throws Exception {
        Mockito.when(request.getHeader(Constans.AUTHORIZATION)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain).doFilter(request, response);
        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_InvalidAuthHeader() throws Exception {
        Mockito.when(request.getHeader(Constans.AUTHORIZATION)).thenReturn(Constans.ADMIN);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain).doFilter(request, response);
        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private static class TestableJwtAuthenticationFilter extends JwtAuthenticationFilter {
        public TestableJwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
            super(jwtService, userDetailsService);
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            super.doFilterInternal(request, response, filterChain);
        }
    }
}