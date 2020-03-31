package com.api.rent.memovie.repository;

import com.api.rent.memovie.model.MovieLikes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para MoviesLikes
 *
 * @author MARIO MENCIA
 */
@Repository
public interface MovieLikesRepository extends JpaRepository<MovieLikes, Long> {

    public List<MovieLikes> findByMovieId(Long movieId);
}
