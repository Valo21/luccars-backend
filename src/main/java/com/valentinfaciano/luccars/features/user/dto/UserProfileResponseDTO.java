package com.valentinfaciano.luccars.features.user.dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponseDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String phone;

  private String address;
  private String city;
  private String state;
  private String postalCode;
  private String country;

  private String imageUrl;

  private Date birthDate;
  private String bio;
}
