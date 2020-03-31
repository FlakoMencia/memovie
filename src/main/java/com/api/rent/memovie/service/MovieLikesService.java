package com.api.rent.memovie.service;

import com.api.rent.memovie.model.MovieLikes;
import java.util.List;

/**
 *
 * @author MARIO MENCIA
 */
public interface MovieLikesService {

    public List<MovieLikes> findAll(String title);

    public List<MovieLikes> findByMovie(Long id);
}
