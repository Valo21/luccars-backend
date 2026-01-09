package com.valentinfaciano.luccars.features.auth;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.valentinfaciano.luccars.config.JwtUtils;
import com.valentinfaciano.luccars.exception.EmailAlreadyInUseException;
import com.valentinfaciano.luccars.exception.InvalidPasswordException;
import com.valentinfaciano.luccars.exception.UserNotFoundException;
import com.valentinfaciano.luccars.features.auth.dto.SignInDTO;
import com.valentinfaciano.luccars.features.auth.dto.SignUpDTO;
import com.valentinfaciano.luccars.features.user.UserRepository;
import com.valentinfaciano.luccars.features.user.entity.User;
import com.valentinfaciano.luccars.shared.dto.JwtDataDTO;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;

  public String generateJwtToken(User user) {
    final String token = jwtUtils.generateJwtToken(user);
    return token;
  }

  public Claims getJwtUser(String token) {
    Claims claims = jwtUtils.getUserEmailFromJwtToken(token);
    return claims;
  }

  public JwtDataDTO signUp(SignUpDTO signUpDTO) {
    Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
    if (user.isPresent()) {
      throw new EmailAlreadyInUseException("User with email " + signUpDTO.getEmail() + " already exists");
    }

    String password = passwordEncoder.encode(signUpDTO.getPassword());

    User newUser = userRepository.save(
        User.builder()
            .email(signUpDTO.getEmail())
            .password(password)
            .build());

    final String token = generateJwtToken(newUser);
    final Claims claims = getJwtUser(token);
    return JwtDataDTO.builder().token(token).claims(claims).build();
  }

  public JwtDataDTO signIn(SignInDTO signInDTO) {
    Optional<User> user = userRepository.findByEmail(signInDTO.getEmail());
    if (user.isEmpty()) {
      throw new UserNotFoundException("User with email " + signInDTO.getEmail() + " not found");
    }

    if (!passwordEncoder.matches(signInDTO.getPassword(), user.get().getPassword())) {
      System.err.println(user.get().getPassword() + " " + signInDTO.getPassword());
      throw new InvalidPasswordException("Invalid password for user with email " + signInDTO.getEmail());
    }

    final String token = generateJwtToken(user.get());
    final Claims claims = getJwtUser(token);
    return JwtDataDTO.builder().token(token).claims(claims).build();
  }
}