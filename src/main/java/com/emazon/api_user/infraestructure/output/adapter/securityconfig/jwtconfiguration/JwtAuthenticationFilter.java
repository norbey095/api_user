package com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration;

import com.emazon.api_user.infraestructure.exceptionhandler.ExceptionResponse;
import com.emazon.api_user.infraestructure.util.ConstantsInfraestructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader(ConstantsInfraestructure.AUTHORIZATION);
            final String jwt;
            final String userName;
            if (authHeader == null || !authHeader.startsWith(ConstantsInfraestructure.BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(ConstantsInfraestructure.VALUE_7);
            userName = jwtService.extractUsername(jwt);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }  catch (Exception ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(ConstantsInfraestructure.APPLICATION_JSON);
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ExceptionResponse(
                    ConstantsInfraestructure.TOKEN_INVALID,
                    HttpStatus.UNAUTHORIZED.toString()
            )));
            return;
        }
        filterChain.doFilter(request, response);
    }
}
