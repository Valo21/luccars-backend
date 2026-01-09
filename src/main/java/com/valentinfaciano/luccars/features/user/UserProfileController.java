package com.valentinfaciano.luccars.features.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valentinfaciano.luccars.features.user.dto.UserProfileCreateDTO;
import com.valentinfaciano.luccars.features.user.dto.UserProfileResponseDTO;
import com.valentinfaciano.luccars.features.user.entity.User;
import com.valentinfaciano.luccars.features.user.entity.UserProfile;
import com.valentinfaciano.luccars.shared.dto.AppResponseDTO;
import com.valentinfaciano.luccars.shared.dto.ResponseHelper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {
  private final UserProfileService userProfileService;

  @GetMapping("/me")
  public ResponseEntity<AppResponseDTO<UserProfileResponseDTO>> me(
      @AuthenticationPrincipal User user) {
    UserProfile profile = userProfileService.findByUser(user);
    UserProfileResponseDTO data = UserProfileResponseDTO.builder()
        .firstName(profile.getFirstName())
        .lastName(profile.getLastName())
        .email(user.getEmail())
        .phone(profile.getPhone())
        .imageUrl(profile.getImageUrl())
        .address(profile.getAddress())
        .city(profile.getCity())
        .state(profile.getState())
        .country(profile.getCountry())
        .postalCode(profile.getPostalCode())
        .bio(profile.getBio())
        .build();
    return ResponseHelper.success(data, "Profile fetched");
  }

  @PostMapping("/me")
  public ResponseEntity<AppResponseDTO<UserProfile>> setup(
      @AuthenticationPrincipal User user, @Valid @RequestBody UserProfileCreateDTO userProfileCreateDTO) {
    UserProfile profile = userProfileService.create(user, userProfileCreateDTO);
    return ResponseHelper.success(profile, "Profile created successfully");
  }
}
