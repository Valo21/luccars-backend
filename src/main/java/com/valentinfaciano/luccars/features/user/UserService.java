package com.valentinfaciano.luccars.features.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.valentinfaciano.luccars.exceptions.UserNotFoundException;
import com.valentinfaciano.luccars.features.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User findByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User with email " + email + " not found");
    }
    return user.get();
  }

  public User findById(UUID userId) {
    Optional<User> user = userRepository.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User with id " + userId + " not found");
    }
    return user.get();
  }
}
