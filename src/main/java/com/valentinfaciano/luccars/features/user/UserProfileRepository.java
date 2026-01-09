package com.valentinfaciano.luccars.features.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valentinfaciano.luccars.features.user.entity.User;
import com.valentinfaciano.luccars.features.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
  Optional<UserProfile> findByUser(User user);
}
