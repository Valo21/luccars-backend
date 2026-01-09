package com.valentinfaciano.luccars.features.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valentinfaciano.luccars.config.CookieUtils;
import com.valentinfaciano.luccars.features.auth.dto.SignInDTO;
import com.valentinfaciano.luccars.features.auth.dto.SignUpDTO;
import com.valentinfaciano.luccars.shared.dto.AppResponseDTO;
import com.valentinfaciano.luccars.shared.dto.JwtDataDTO;
import com.valentinfaciano.luccars.shared.dto.ResponseHelper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final CookieUtils cookieUtils;

  @PostMapping("/signup")
  public ResponseEntity<AppResponseDTO<Claims>> signUp(@Valid @RequestBody SignUpDTO signUpDTO,
      HttpServletResponse response) {
    JwtDataDTO jwtData = authService.signUp(signUpDTO);
    cookieUtils.addJwtCookie(response, jwtData.getToken(), 60 * 60);
    return ResponseHelper.success(jwtData.getClaims(), "User registered successfully");
  }

  @PostMapping("/signin")
  public ResponseEntity<AppResponseDTO<Claims>> signIn(@Valid @RequestBody SignInDTO signInDTO,
      HttpServletResponse response) {
    JwtDataDTO jwtData = authService.signIn(signInDTO);
    cookieUtils.addJwtCookie(response, jwtData.getToken(), 60 * 60); // 1 hora
    return ResponseHelper.success(jwtData.getClaims(), "User signed in successfully");
  }

  @PostMapping("/logout")
  public ResponseEntity<AppResponseDTO<String>> logout(HttpServletResponse response) {
    cookieUtils.clearJwtCookie(response);
    return ResponseHelper.success("Logged out successfully", "Logged out successfully");
  }
}