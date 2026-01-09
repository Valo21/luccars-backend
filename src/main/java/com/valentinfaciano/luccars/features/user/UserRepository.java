package com.valentinfaciano.luccars.features.user;

import com.valentinfaciano.luccars.features.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  // Buscar usuario por email
  Optional<User> findByEmail(String email);

  // Verificar si existe un usuario por email
  boolean existsByEmail(String email);
}
