package com.api.rent.memovie.service;

import com.api.rent.memovie.model.MovieLikes;
import com.api.rent.memovie.repository.MovieLikesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para Likes de movies
 *
 * @author MARIO MENCIA
 */
@Service
public class MovieLikesServiceImpl implements MovieLikesService {

    @Autowired
    private MovieLikesRepository movieLikesRepository;

    @Override
    public List<MovieLikes> findAll(String title) {
        return (List<MovieLikes>) movieLikesRepository.findAll();
    }

    @Override
    public List<MovieLikes> findByMovie(Long movieId) {
        return (List<MovieLikes>) movieLikesRepository.findByMovieId(movieId);

    }

}
