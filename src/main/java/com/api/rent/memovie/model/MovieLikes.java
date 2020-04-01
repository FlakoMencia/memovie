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
 *
 * @author MARIO MENCIA
 */
@Entity
@Table(name = "movie_likes")
@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class MovieLikes implements Serializable {

    private static final long serialVersionUID = 2421797954080517299L;

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Boolean likethismovie;


    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", nullable = false, foreignKey = @ForeignKey(name = "FK_USERLIKES"))})
    private Movie movie;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "USERLIKE_ID", referencedColumnName = "ID", nullable = false , foreignKey = @ForeignKey(name = "FK_USERLIKES"))})
    private UserPrincipal user;
 
}
