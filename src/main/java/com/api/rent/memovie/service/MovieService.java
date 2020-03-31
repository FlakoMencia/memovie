
package com.api.rent.memovie.service;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.utilities.DetailMoviePojo;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author MARIO MENCIA
 */
public interface MovieService {

    public List<Movie> findAll();

    public Movie save(Movie newMovie);

    public DetailMoviePojo findDetails(Long id);

    public boolean existsById(Long id);

    public Movie getOne(Long id);


    public Page<Movie> findAllMoviesByAvailability(Integer pageNumber, Integer pageSize, String sortBy, String ordered, Boolean filterByAvailability, Boolean available);

    
}
