package com.valentinfaciano.luccars.config;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

  public void addJwtCookie(HttpServletResponse response, String token, int maxAgeSeconds) {
    Cookie jwtCookie = new Cookie("jwt", token);
    // jwtCookie.setHttpOnly(true);
    // jwtCookie.setSecure(true);
    jwtCookie.setPath("/");
    jwtCookie.setMaxAge(maxAgeSeconds);
    response.addCookie(jwtCookie);
  }

  public void clearJwtCookie(HttpServletResponse response) {
    Cookie jwtCookie = new Cookie("jwt", null);
    // jwtCookie.setHttpOnly(true);
    // jwtCookie.setSecure(true);
    jwtCookie.setPath("/");
    jwtCookie.setMaxAge(0);
    response.addCookie(jwtCookie);
  }
}
