
package com.api.rent.memovie.service;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import java.util.List;

/**
 *
 * @author MARIO MENCIA
 */
public interface MovieService {

    public List<Movie> findAll();

    public Movie save(Movie newMovie);

    
}
