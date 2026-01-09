package com.valentinfaciano.luccars.features.user.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserProfileCreateDTO {

  @NotBlank(message = "First name is required")
  @Size(max = 50)
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Size(max = 50)
  private String lastName;

  @NotBlank(message = "Phone is required")
  @Size(max = 20)
  private String phone;

  @Past(message = "Birth date must be in the past")
  private Date birthDate;

  @NotBlank(message = "Address is required")
  private String address;

  @NotBlank(message = "City is required")
  private String city;

  @NotBlank(message = "State is required")
  private String state;

  @NotBlank(message = "Postal code is required")
  private String postalCode;

  @NotBlank(message = "Country is required")
  private String country;

  private String imageUrl;

  @Size(max = 500)
  private String bio;
}
