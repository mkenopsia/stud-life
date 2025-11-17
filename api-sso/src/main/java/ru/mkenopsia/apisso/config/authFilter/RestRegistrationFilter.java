package ru.mkenopsia.apisso.config.authFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.mkenopsia.apisso.dto.RegisterRequest;
import ru.mkenopsia.apisso.dto.RegisterResponse;
import ru.mkenopsia.apisso.service.AuthService;
import ru.mkenopsia.apisso.exception.UserCredentialsValidationException;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class RestRegistrationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper mapper;
    private final PayloadValidator validator;
    private final SecurityContextRepository securityContextRepository;
    private final AuthService authService;

    public RestRegistrationFilter(final ObjectMapper mapper,
                                  final PayloadValidator validator,
                                  final SecurityContextRepository repository,
                                  final AuthService authService,
                                  final AuthenticationManager authenticationManager) {
        super("/api/auth/register", authenticationManager);
        this.mapper = mapper;
        this.validator = validator;
        this.securityContextRepository = repository;
        this.authService = authService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        return super.attemptAuthentication(request, response);
        try {
            RegisterRequest userPayload = mapper.readValue(request.getInputStream(), RegisterRequest.class);
            validator.validate(userPayload);

            authService.registerUser(userPayload.username(), userPayload.email(), userPayload.password());
            log.info("Зарегестрирован пользователь {}, с email: {}", userPayload.username(), userPayload.email());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(userPayload.username(), userPayload.password()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid JSON format", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.getWriter().write(
                mapper.writeValueAsString(
                        new RegisterResponse(authResult.getName())
                )
        );
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
//        super.unsuccessfulAuthentication(request, response, failed);
        String message = "Error";
        log.info("Неудачаная попытка авторизации", failed);
        if (failed instanceof BadCredentialsException || failed instanceof UsernameNotFoundException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            message = "wrong username or password";
        } else if (failed instanceof UserCredentialsValidationException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            System.out.println(failed.getMessage());
            message = "invalid data";
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            message = "Unexpected error";
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(Map.of("message", message)));
    }
}
