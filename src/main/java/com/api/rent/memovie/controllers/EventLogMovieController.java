package com.api.rent.memovie.controllers;

import com.api.rent.memovie.enums.EventTypeEnum;
import com.api.rent.memovie.model.EventLogMovie;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.service.EventLogMovieService;
import com.api.rent.memovie.service.MovieService;
import com.api.rent.memovie.utilities.CommonUtilities;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de movimientos de peliculas
 *
 * @author MARIO MENCIA
 */
@Log
@RestController
@RequestMapping("/event/log")
@Getter
@Setter
public class EventLogMovieController {

    @Autowired
    private EventLogMovieService eventLogMovieService;

    @Autowired
    private MovieService movieService;

    @Value("${app.amount.penalty.per.day}")
    private Double penaltyperday;

    /**
     * metodo para agregar un movimiento de pelicula
     *
     * @param eventLog
     * @return Response (content: EventLogMovie)
     */
    @PostMapping({"/transaction"})
    public ResponseEntity<?> registTransaction(@RequestBody EventLogMovie eventLog) {
        if (!EventTypeEnum.SALES.getCode().equals(eventLog.getEventType())
                && !EventTypeEnum.RENT.getCode().equals(eventLog.getEventType())) {
            return new ResponseEntity<>("Type of transaccion Invalid!", HttpStatus.NOT_ACCEPTABLE);
        }
        if (eventLog.getMovieId() == null || !movieService.existsById(eventLog.getMovieId())) {
            return new ResponseEntity<>("Movie not Found!", HttpStatus.NOT_FOUND);
        }

        Movie movie = movieService.getOne(eventLog.getMovieId());
        Date now = new Date();
        eventLog.setEventDay(now);
        eventLog.setDeservesPenalty(Boolean.FALSE);
        eventLog.setAmountSales(movie.getSalesPrice());
        eventLog.setAmountRent(movie.getRentalPrice());
        eventLog.setAmountPenaltyPerday(0.00);
        eventLog.setTotalTransaction(movie.getSalesPrice());
        if (EventTypeEnum.RENT.getCode().equals(eventLog.getEventType())) {
            eventLog.setAmountPenaltyPerday(penaltyperday);
            eventLog.setStarDate(now);
            eventLog.setFinalDate(CommonUtilities.obtenerFechaFinal(now, 5));
            eventLog.setTotalTransaction(movie.getRentalPrice());
        }
        eventLog = eventLogMovieService.save(eventLog);
        if (eventLog.getId() != null) {
            movie.setStock(movie.getStock() - 1);
            movieService.save(movie);
        }
        return new ResponseEntity<EventLogMovie>(eventLog, HttpStatus.CREATED);
    }

    /**
     * metodo para retornar de pelicula
     *
     * @param eventLog
     * @return
     */
    @PutMapping({"/return/movie"})
    public ResponseEntity<?> returnMovie(@RequestBody EventLogMovie eventLog) {
        if (!eventLogMovieService.existsById(eventLog.getId())) {
            return new ResponseEntity<>("Event of transaccion not found!", HttpStatus.NOT_FOUND);
        }
        if (!EventTypeEnum.RENT.getCode().equals(eventLog.getEventType())) {
            return new ResponseEntity<>("Type of transaccion Invalid!", HttpStatus.NOT_ACCEPTABLE);
        }
        Date now = new Date();
        eventLog = eventLogMovieService.getOne(eventLog.getId());
        eventLog.setReturnDate(now);
        String result = "Thanks for delivering on time";
        if (now.after(eventLog.getFinalDate())) {
            eventLog.setDeservesPenalty(Boolean.TRUE);
            Double penalty = CommonUtilities.calcularPenalidad(now, eventLog.getFinalDate(), eventLog.getAmountPenaltyPerday());
            eventLog.setTotalTransaction(eventLog.getTotalTransaction() + penalty);
            result = "Must pay: $" + penalty + " for DELAY PENALTY! Your new total amount is: $" + eventLog.getTotalTransaction();
        }
        Movie movie = movieService.getOne(eventLog.getMovieId());
        movie.setStock(movie.getStock() + 1);
        movieService.save(movie);

        return new ResponseEntity<EventLogMovie>(eventLog, HttpStatus.OK);
    }

}
