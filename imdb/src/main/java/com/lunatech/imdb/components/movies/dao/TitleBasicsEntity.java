package com.lunatech.imdb.components.movies.dao;

import com.lunatech.imdb.common.constants.ApiConstants;
import com.lunatech.imdb.components.movies.dto.Movie;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@Entity
@Table(name = "title_basics")
@Data
@ToString(exclude = ("titlePrincipals"))
public class TitleBasicsEntity {
    @Id
    private String tconst;

    @Column(name = "title_type")
    private String titleType;

    @Column(name = "primary_title")
    private String primaryTitle;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "is_adult")
    private Boolean isAdult;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "runtime_minutes")
    private Integer runtimeMinutes;

    @Column(name = "genres")
    private String genres;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tconst", referencedColumnName = "tconst")
    private TitleRatingEntity titleRating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tconst", referencedColumnName = "tconst")
    private TitleCrewEntity titleCrew;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tconst", referencedColumnName = "tconst")
    private List<TitlePrincipalsEntity> titlePrincipals;

}
