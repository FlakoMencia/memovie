package com.api.rent.memovie.repository;

import com.api.rent.memovie.model.Movie;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio de movies
 *
 * @author MARIO MENCIA
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie , Long> {

    public List<Movie> findByTittle(String title);

    public Page<Movie> findByAvailability(Pageable paging, Boolean available);
    
    
    
}
