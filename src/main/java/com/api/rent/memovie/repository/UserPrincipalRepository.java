package com.api.rent.memovie.repository;

import com.api.rent.memovie.model.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para Tabla Usuario
 *
 * @author MARIO MENCIA
 */
@Repository
public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Long> {

    public UserPrincipal findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

}
