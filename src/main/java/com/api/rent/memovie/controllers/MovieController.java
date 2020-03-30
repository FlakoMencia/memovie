package com.api.rent.memovie.controllers;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.service.CastService;
import com.api.rent.memovie.service.MovieService;
import java.util.List;
import java.util.logging.Level;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de Movies
 *
 * @author MARIO MENCIA
 */
@Log
@RestController
@RequestMapping("/movies")
@Getter
@Setter
public class MovieController {
    
    @Autowired 
    private MovieService movieService;
    
    @Autowired 
    private CastService castService;

    
    /**
     *  Metodo para obener todas las peliculas existentes
     * @return List<Movie>
     */
    @GetMapping({"/all"})
    public List<Movie> all() {
        System.out.println("LLEGO AL ALL");
        return movieService.findAll();
    }

    
    @PostMapping({"/add"})
    public Movie addMovie(@RequestBody Movie newMovie) {        
        log.log(Level.INFO, "*** Se han agregado " + newMovie.getStock()+ " disponibles para: "+ newMovie.getTittle() );
        return movieService.save(newMovie);
    }
    
    /**
     *  Metodo para obtener el elenco principales por pelicula     * 
     * @param id
     * @param model
     * @return ist<Cast> 
     */
    @GetMapping({"/{id}/cast"})
    public List<Cast> obtenerElenco(@PathVariable Long id, Model model ) {
//        System.out.println("*** elenco by " + id);
        return castService.findByMovie(id);
    }
  
    /**
     *  Metodo para agregar un actor por pelicula
     * @param id
     * @return Cast
     */ 
    @PostMapping({"/{id}/cast"})
    public Cast obtenerElenco(@RequestBody Cast newCast) {        
//        System.out.println("*** Guardando " + newCast.getRealName());
        return castService.save(newCast);
    }
}
