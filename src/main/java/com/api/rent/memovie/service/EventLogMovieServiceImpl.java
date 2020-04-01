package com.api.rent.memovie.service;

import com.api.rent.memovie.model.EventLogMovie;
import com.api.rent.memovie.repository.EventLogMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para los movimientos de movies
 *
 * @author MARIO MENCIA
 */
@Service
public class EventLogMovieServiceImpl implements EventLogMovieService {

    @Autowired
    private EventLogMovieRepository eventLogMovieRepository;

    @Override
    public EventLogMovie save(EventLogMovie eventLog) {
        return eventLogMovieRepository.save(eventLog);
    }

    @Override
    public boolean existsById(Long id) {
        return eventLogMovieRepository.existsById(id);
    }

    @Override
    public EventLogMovie getOne(Long id) {
        return eventLogMovieRepository.getOne(id);
    }

}
