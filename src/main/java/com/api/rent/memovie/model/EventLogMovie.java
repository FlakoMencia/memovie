package com.api.rent.memovie.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.common.util.JsonDateSerializer;

/**
 * Entidad para los movimientos de peliculas
 *
 * @author MARIO MENCIA
 */
@Entity
@Table(name = "event_log_movie")
@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class EventLogMovie implements Serializable {

    private static final long serialVersionUID = -1871014760322702571L;

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(nullable = false)
    private Date eventDay;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(nullable = true)
    private Date StarDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(nullable = true)
    private Date finalDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(nullable = true)
    private Date returnDate;

    @Column(nullable = false)
    private Double amountPenaltyPerday;

    @Column(nullable = false)
    private Double amountRent;

    @Column(nullable = false)
    private Double amountSales;

    @Column(nullable = true)
    private Boolean deservesPenalty;

    @Column(nullable = false)
    private Double totalTransaction;
    
    @Column(nullable = false)
    private Long movieId;
    
    @Column(nullable = false)
    private String username;
    
    

//    @ManyToOne
//    @JoinColumns({
//        @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", nullable = false, foreignKey = @ForeignKey(name = "FK_LOGMOVIE"))})
//    private Movie movie;
//
//    @ManyToOne
//    @JoinColumns({
//        @JoinColumn(name = "USERLIKE_ID", referencedColumnName = "ID", nullable = false, foreignKey = @ForeignKey(name = "FK_LOGUSER"))})
//    private UserPrincipal user;


}
