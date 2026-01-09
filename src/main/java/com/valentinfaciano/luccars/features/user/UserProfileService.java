package com.valentinfaciano.luccars.features.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.valentinfaciano.luccars.exceptions.UserNotFoundException;
import com.valentinfaciano.luccars.features.user.dto.UserProfileCreateDTO;
import com.valentinfaciano.luccars.features.user.entity.User;
import com.valentinfaciano.luccars.features.user.entity.UserProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {
  private final UserProfileRepository userProfileRepository;

  public UserProfile findByUser(User user) {
    Optional<UserProfile> userProfile = userProfileRepository.findByUser(user);
    if (userProfile.isEmpty()) {
      throw new UserNotFoundException("User profile not found");
    }
    return userProfile.get();
  }

  public UserProfile create(User user, UserProfileCreateDTO userProfileCreateDTO) {
    UserProfile userProfile = UserProfile.builder()
        .user(user)
        .firstName(userProfileCreateDTO.getFirstName())
        .lastName(userProfileCreateDTO.getLastName())
        .phone(userProfileCreateDTO.getPhone())
        .birthDate(userProfileCreateDTO.getBirthDate())
        .address(userProfileCreateDTO.getAddress())
        .city(userProfileCreateDTO.getCity())
        .state(userProfileCreateDTO.getState())
        .postalCode(userProfileCreateDTO.getPostalCode())
        .country(userProfileCreateDTO.getCountry())
        .imageUrl(userProfileCreateDTO.getImageUrl())
        .bio(userProfileCreateDTO.getBio())
        .build();
    return userProfileRepository.save(userProfile);
  }
}