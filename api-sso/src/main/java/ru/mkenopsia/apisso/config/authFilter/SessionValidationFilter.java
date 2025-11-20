package ru.mkenopsia.apisso.config.authFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SessionValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/api/auth/validate")) {
            if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                response.setStatus(HttpStatus.OK.value());
            }
            return;
        }

        filterChain.doFilter(request, response);
    }
}
