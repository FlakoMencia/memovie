package com.api.rent.memovie.service;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.model.MovieLikes;
import com.api.rent.memovie.repository.CastRepository;
import com.api.rent.memovie.repository.MovieLikesRepository;
import com.api.rent.memovie.repository.MovieRepository;
import com.api.rent.memovie.pojo.DetailMoviePojo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para Movie
 *
 * @author MARIO MENCIA
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieLikesRepository movieLikesRepository;

    @Autowired
    private CastRepository castRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Page<Movie> findAllMoviesByAvailability(Integer pageNumber, Integer pageSize, String sortBy, String ordered, Boolean filterByAvailability, Boolean available) {
        Sort sort = Sort.by((new Sort.Order(("ASC".equalsIgnoreCase(ordered) ? Sort.Direction.ASC : Sort.Direction.DESC), sortBy).nullsLast()));
        Page<Movie> ret = null;
        if (filterByAvailability) {
            Pageable paging = PageRequest.of(pageNumber, pageSize, sort);
            ret = movieRepository.findByAvailability(paging, available);
        } else {
            ret = movieRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));

        }
        return ret;
    }

    @Override
    public Movie save(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie getOne(Long movieId) {
        return movieRepository.getOne(movieId);
    }

    @Override
    @Transactional(readOnly = true)
    public DetailMoviePojo findDetails(Long movieId) {
        DetailMoviePojo ret = new DetailMoviePojo();
//        ret.setMovie(movieRepository.findById(movieId).orElse(new Movie()));
        ret.setMovie(movieRepository.getOne(movieId));
        List<Cast> castOfMovie = castRepository.findByMovieId(movieId);
        List<MovieLikes> likesOfMovie = movieLikesRepository.findByMovieId(movieId);
        ret.setCastofmovie((castOfMovie != null ? castOfMovie : new ArrayList<>()));
        ret.setLikesofmovie(likesOfMovie != null ? likesOfMovie : new ArrayList<>());
        return ret;
    }

    @Override
    public boolean existsById(Long movieId) {
        return movieRepository.existsById(movieId);
    }

    @Override
    @Transactional
    public void deleteById(Long movieId) {
        movieLikesRepository.removeByMovieId(movieId);
        castRepository.removeByMovieId(movieId);
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> findByTittleContaining(String tags) {
       return movieRepository.findByTittleContaining(tags);
               
    }

}
