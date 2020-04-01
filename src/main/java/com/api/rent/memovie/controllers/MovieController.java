package com.api.rent.memovie.controllers;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.service.CastService;
import com.api.rent.memovie.service.MovieLikesService;
import com.api.rent.memovie.service.MovieService;
import com.api.rent.memovie.pojo.DetailMoviePojo;
import java.util.List;
import java.util.logging.Level;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private MovieLikesService movieLikesService;

    /**
     * Metodo para obtener peliculas Disponibles para Renta o Compra
     *
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param ordered
     * @return Response (content: Page<Movie>)
     */
    @GetMapping({"/all"})
    public ResponseEntity<Page<Movie>> moviesAvailable(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "tittle", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String ordered) {
        Boolean filterByAvailability = true;
        Boolean available = true;
        Page<Movie> moviesByAvailability = movieService.findAllMoviesByAvailability(pageNumber, pageSize, sortBy, ordered, filterByAvailability, available);
        return new ResponseEntity<Page<Movie>>(moviesByAvailability, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Metodo para obtener todo el catalogo de peliculas con la posibilidad de
     * filtrar por dsiponibilidad y/o ordenar por disponibilidad PARA
     * ADMINISTRADOR
     *
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param ordered
     * @param filterByAvailability
     * @param enabled
     * @return Response (content: Page<Movie>)
     */
    @GetMapping({"/admin/all"})
    public ResponseEntity<?> moviesAdminByAvailability(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "tittle", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String ordered,
            @RequestParam(defaultValue = "false", required = false) Boolean filterByAvailability,
            @RequestParam(defaultValue = "true", required = false) Boolean available) {
        Page<Movie> moviesByAvailability = movieService.findAllMoviesByAvailability(pageNumber, pageSize, sortBy, ordered, filterByAvailability, available);

        return new ResponseEntity<Page<Movie>>(moviesByAvailability, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Metodo para acceder a los detalles por pelicula
     *
     * @param id
     * @param model
     * @return Response (content: DetailMoviePojo)
     */
    @GetMapping({"/{id}/detail"})
    public ResponseEntity<?> movieDetails(@PathVariable Long id, Model model) {
        DetailMoviePojo detailPojo = null;
        if (movieService.existsById(id)) {
            detailPojo = movieService.findDetails(id);
        } else {
            return new ResponseEntity<String>("The movie doesn't exist!", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DetailMoviePojo>(detailPojo, HttpStatus.OK);
    }

    /**
     * Metodo para habilitar o deshabilitar una pelicula
     *
     * @param id
     * @param availability
     * @return Response (content: Movie)
     */
    @PutMapping({"/admin/{id}/{enable}"})
    public ResponseEntity<?> enableMovie(@PathVariable("id") Long id, @PathVariable("enable") int availability) {
        if (movieService.existsById(id)) {
            Movie movie = movieService.getOne(id);
            Boolean available = (availability == 1 ? Boolean.TRUE : Boolean.FALSE);
            movie.setAvailability(available);
            movieService.save(movie);
            return new ResponseEntity<Movie>(movie, HttpStatus.ACCEPTED);
        } else {
//        Map<String, Object> response = new HashMap<>();
//            response.put("Error", "The movie doesn't exist");
            return new ResponseEntity<String>("The movie doesn't exist!", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adicion de movie y disponibilidad de stock
     *
     * @param newMovie
     * @return Response (content: Movie)
     */
    @PostMapping({"/admin/add"})
    public ResponseEntity<?> addMovie(@RequestBody Movie newMovie) {
        try {
            movieService.save(newMovie);
            log.log(Level.INFO, "*** Se han agregado " + newMovie.getStock() + " disponibles para: " + newMovie.getTittle());
        } catch (Exception e) {
            return new ResponseEntity<String>(("Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Movie>(newMovie, HttpStatus.CREATED);
    }

    /**
     * Metodo para actualizar pelicula
     *
     * @param movie
     * @return
     */
    @PutMapping({"admin/{id}"})
    public ResponseEntity<?> modifyMovie(@Valid @RequestBody Movie movie) {
        movieService.save(movie);
        return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
    }

    /**
     * Metodo para eliminar totalmente una pelicula del catalogo
     *
     * @param id
     * @return Response (Con el exito o error)
     */
    @DeleteMapping({"/admin/{id}"})
    public ResponseEntity<?> deleteMove(@PathVariable Long id
    ) {
        if (movieService.existsById(id)) {
            movieService.deleteById(id);
        } else {
            return new ResponseEntity<>("The movie doesn't exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Movie has been deleted!", HttpStatus.ACCEPTED);
    }

    /**
     * Metodo para obtener el elenco principales por pelicula
     *
     * @param id
     * @param model
     * @return ist<Cast>
     */
    @GetMapping({"/{id}/cast"})
    public List<Cast> obtenerElenco(@PathVariable Long id
    ) {
        return castService.findByMovie(id);
    }

    /**
     * Metodo para agregar un actor por pelicula
     *
     * @param id
     * @return Cast
     */
    @PostMapping({"/{id}/cast"})
    public ResponseEntity<?> obtenerElenco(@RequestBody Cast newCast
    ) {
        try {
            castService.save(newCast);
        } catch (Exception e) {
            return new ResponseEntity<String>(("Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cast>(newCast, HttpStatus.CREATED);
    }
}
