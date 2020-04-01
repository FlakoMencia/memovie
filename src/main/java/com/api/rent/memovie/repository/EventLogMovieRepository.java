package com.api.rent.memovie.repository;

import com.api.rent.memovie.model.EventLogMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Respositorio para los movimientos de peliculas
 *
 * @author MARIO MENCIA
 */
@Repository
public interface EventLogMovieRepository extends JpaRepository<EventLogMovie , Long>{

   

}
