package com.api.rent.memovie.service;

import com.api.rent.memovie.model.EventLogMovie;

/**
 *
 * @author MARIO MENCIA
 */
public interface EventLogMovieService {

    public EventLogMovie save(EventLogMovie eventLog);

    public boolean existsById(Long id);

    public EventLogMovie getOne(Long id);
    
}
