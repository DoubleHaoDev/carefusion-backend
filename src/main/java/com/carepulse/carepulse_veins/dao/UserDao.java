package com.carepulse.carepulse_veins.dao;

import com.carepulse.carepulse_veins.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

}
