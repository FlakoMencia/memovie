package com.api.rent.memovie.service;


import com.api.rent.memovie.model.Cast;
import java.util.List;

/**
 *
 * @author MARIO MENCIA
 */
public interface CastService {

    public List<Cast> findAll();

    public List<Cast> findByMovie(Long movieId);

    public Cast save(Cast newCast);
    
}
