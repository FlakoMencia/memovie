package com.api.rent.memovie.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Entidad de tabla Movies
 *
 * @author MARIO MENCIA
 */
@Entity
@Table(name = "movies")
@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Movie implements Serializable {

    private static final long serialVersionUID = -2305882057440677171L;

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String tittle;

    @NotEmpty
    @Column(nullable = false, length = 1000)
    private String sinopsis;

    @Column(nullable = true)
    private byte[] cover;

    @Column(nullable = false)
    private Double rental_price;

    @Column(nullable = false)
    private Double sales_price;

    @Column(nullable = false)
    private Integer Stock;
    
    @Column(nullable = false)
    private Boolean availability;
    
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumns({
//        @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USERLIKES"))   
//    })
//    private List<MovieLikes> likes = new ArrayList<>();
//    
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumns({
//        @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name="FK_MOVIECAST"))
//    })
//    private List<Cast> casts = new ArrayList<>();
    
    
}
