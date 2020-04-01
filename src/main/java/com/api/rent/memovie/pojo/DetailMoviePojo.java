package com.api.rent.memovie.pojo;

import com.api.rent.memovie.model.Cast;
import com.api.rent.memovie.model.Movie;
import com.api.rent.memovie.model.MovieLikes;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Util para enviar detalle completo de pelicula
 *
 * @author MARIO MENCIA
 */

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class DetailMoviePojo implements Serializable{
    
    private static final long serialVersionUID = -6255466929147915694L;
    
    @NonNull
    private Movie movie;
    
    @NonNull
    private List<Cast> castofmovie;
    
    @NonNull    
    private List<MovieLikes> likesofmovie;
    
    
}
