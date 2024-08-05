package com.carefusion.carefusion_backend.dao;

import com.carefusion.carefusion_backend.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

}
