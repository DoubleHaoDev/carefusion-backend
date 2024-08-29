package com.carefusion.carefusion_backend.dao.security;

import com.carefusion.carefusion_backend.model.entity.security.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDao extends JpaRepository<Token, Long> {
  Long deleteByUserId(Long userId);

  Optional<Token> findByJwtToken(String jwtToken);
}
