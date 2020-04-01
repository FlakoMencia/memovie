package com.api.rent.memovie.repository;

import com.api.rent.memovie.model.Cast;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARIO MENCIA
 */
@Repository
public interface CastRepository extends  JpaRepository<Cast , Long> {
    

    public List<Cast>findByMovieId(Long movieId);

    public Long removeByMovieId( Long movieId);
    
    
}
