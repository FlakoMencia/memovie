package com.api.rent.memovie.service;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.repository.CastRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del service para Elenco
 *
 * @author MARIO MENCIA
 */
@Service
public class CastServiceImpl implements CastService {

    @Autowired
    private CastRepository castRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cast> findAll() {
        return (List<Cast>) castRepository.findAll();

    }

    @Override
    public List<Cast> findByMovie(Long movieId) {
        return (List<Cast>) castRepository.findByMovieId(movieId);
    }

    @Override
    public Cast save(Cast newCast) {
        return castRepository.save(newCast);

    }

}
