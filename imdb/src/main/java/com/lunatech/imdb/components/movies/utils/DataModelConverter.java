package com.lunatech.imdb.components.movies.utils;

import com.lunatech.imdb.common.constants.ApiConstants;
import com.lunatech.imdb.common.error.exceptions.NameNotFoundException;
import com.lunatech.imdb.components.movies.dao.TitleBasicsEntity;
import com.lunatech.imdb.components.movies.dao.TitleCrewEntity;
import com.lunatech.imdb.components.movies.dao.TitlePrincipalsEntity;
import com.lunatech.imdb.components.movies.dto.Crew;
import com.lunatech.imdb.components.movies.dto.CrewMember;
import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;
import com.lunatech.imdb.components.names.NamesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@Component
@Slf4j
public class DataModelConverter {

    private final NamesService namesService;

    @Autowired
    public DataModelConverter(NamesService namesService) {
        this.namesService = namesService;
    }

    public Movie toMovieModel(TitleBasicsEntity entity) {

        return Movie.builder()
                .id(entity.getTconst())
                .originalName(entity.getOriginalTitle())
                .primaryName(entity.getPrimaryTitle())
                .type(entity.getTitleType())
                .genres(entity.getGenres() != null ? Arrays.asList(entity.getGenres().split(ApiConstants.GENRES_SPLITTER)) : new ArrayList<>())
                .crew(getCrew(entity.getTitleCrew()))
                .cast(entity.getTitlePrincipals().stream().map(TitlePrincipalsEntity::toModel).collect(Collectors.toList()))
                .build();
    }

    public TopRatedMovie toTopRatedMovieModel(TitleBasicsEntity entity) {

        return TopRatedMovie.builder()
                .id(entity.getTconst())
                .primaryName(entity.getPrimaryTitle())
                .genres(entity.getGenres() != null ? Arrays.asList(entity.getGenres().split(ApiConstants.GENRES_SPLITTER)) : new ArrayList<>())
                .rating(entity.getTitleRating().getAverageRating())
                .votes(entity.getTitleRating().getNumVotes())
                .build();
    }

    Crew getCrew(TitleCrewEntity entity) {
        List<String> directors = entity.getDirectors() != null ?
                Arrays.asList(entity.getDirectors().split(ApiConstants.DIRECTORS_SPLITTER)) : new ArrayList<>();

        List<String> writers = entity.getWriters() != null ?
                Arrays.asList(entity.getWriters().split(ApiConstants.WRITERS_SPLITTER)) : new ArrayList<>();

        return Crew.builder()
                .directors(getCrewMembersNameDetails(directors))
                .writers(getCrewMembersNameDetails(writers))
                .build();
    }

    List<CrewMember> getCrewMembersNameDetails(List<String> ids){
        List<CrewMember> crewDetails = new ArrayList<>();
        for (String id : ids) {
            try {
                crewDetails.add(namesService.getNameDetails(id).toCrewMemberModel());
            } catch (NameNotFoundException e) {
                log.error("Name basics with [nconst] id {} not exist in the db!", id);
            }
        }
        return crewDetails;
    }
}
