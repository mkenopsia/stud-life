package ru.mkenopsia.apisso.config.authFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.mkenopsia.apisso.dto.LoginRequest;
import ru.mkenopsia.apisso.dto.RegisterResponse;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class RestLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final String LOGIN_PATH = "/api/auth/login";
    private final ObjectMapper mapper;
    private final SecurityContextRepository securityContextRepository;

    public RestLoginFilter(AuthenticationManager authenticationManager,
                           ObjectMapper objectMapper,
                           SecurityContextRepository repository) {
        super(authenticationManager);
        setFilterProcessesUrl(LOGIN_PATH);
        this.mapper = objectMapper;
        this.securityContextRepository = repository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if(!request.getMethod().equals("POST") || !request.getContentType().contains("application/json")) {
            throw new AuthenticationServiceException("api.auth.error.content-type.incorrect");
        }

        try(InputStream is = request.getInputStream()) {
            LoginRequest loginRequest = mapper.readValue(is, LoginRequest.class);
            log.info("Пользователь с данными {} пытается аутентифицироваться", loginRequest.identifier());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
              loginRequest.identifier(), loginRequest.password()
            );
            return  this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException("api.invalid-data");
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
                                              AuthenticationException failed) {
        log.info("ошибка", failed);
    }
}
