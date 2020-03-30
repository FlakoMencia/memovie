package com.api.rent.memovie.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad para elenco de pelicula
 *
 * @author MARIO MENCIA
 */
@Entity
@Table(name = "cast_of_movie")
@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Cast implements Serializable {

    private static final long serialVersionUID = 7250591337515052513L;

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Favor ingrese el nombre Real del personaje")
    @Column(nullable = false)
    private String realName;

    @NotEmpty(message = "Favor ingrese el nombre del personaje")
    @Column(nullable = false)
    private String characterName;

    @Column(nullable = true)
    private byte[] photoActor;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", nullable = false , foreignKey = @ForeignKey(name = "FK_MOVIECAST"))})
    private Movie movie;


}
