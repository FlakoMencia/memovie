package com.api.rent.memovie.repository;

import com.api.rent.memovie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para Tabla Usuario
 *
 * @author MARIO MENCIA
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
       
}
