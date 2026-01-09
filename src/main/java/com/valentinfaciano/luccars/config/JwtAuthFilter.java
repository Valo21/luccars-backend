package com.valentinfaciano.luccars.config;

import com.valentinfaciano.luccars.features.user.UserService;
import com.valentinfaciano.luccars.features.user.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = extractJwtFromCookie(request);

            if (token != null && jwtUtils.validateJwtToken(token)) {
                String email = jwtUtils.getUserEmailFromJwtToken(token);

                User user = userService.findByEmail(email);

                // Convertir roles a GrantedAuthority
                List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                        .flatMap(role -> role.getPermissions().stream())
                        .map(permission -> new SimpleGrantedAuthority(
                                permission.getName().name()))
                        .toList();

                System.out.println(authorities);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}" + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
