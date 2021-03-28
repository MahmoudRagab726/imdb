package com.lunatech.imdb.components.movies;

import com.lunatech.imdb.common.constants.CacheNames;
import com.lunatech.imdb.common.enums.TitleGenres;
import com.lunatech.imdb.common.enums.TitleTypes;
import com.lunatech.imdb.common.error.exceptions.InvalidQueryParamException;
import com.lunatech.imdb.components.movies.dao.TitleBasicsEntity;
import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;
import com.lunatech.imdb.components.movies.utils.DataModelConverter;
import com.lunatech.imdb.components.movies.utils.TitleBasicsQueryBuilderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final TitleBasicsQueryBuilderManager titleBasicsBuilder;
    private final TitleBasicsRepository titleBasicsRepository;
    private final DataModelConverter dataModelConverter;



    @Override
    @Cacheable(value = CacheNames.MOVIES_SEARCH, key = "#primaryTitle+','+#originalTitle+','+#offset+','+#limit")
    public List<Movie> getMovies(String primaryTitle, String originalTitle, Integer offset, Integer limit) {

        Pageable paging = PageRequest.of(offset, limit, Sort.by("startYear").ascending());

        Specification<TitleBasicsEntity> query = titleBasicsBuilder.prepareGetMoviesQuery(TitleTypes.MOVIE, primaryTitle, originalTitle);
        List<TitleBasicsEntity> movies = titleBasicsRepository.findAll(query, paging).getContent();
        log.info("Query returned list with size {}", movies.size());

        return movies.stream().map(dataModelConverter::toMovieModel).collect(Collectors.toList());

    }

    @Override
    @Cacheable(value = CacheNames.TOP_RATED_MOVIES, key = "#genre+','+#offset+','+#limit")
    public List<TopRatedMovie> getTopRatedMovies(String genre, Integer offset, Integer limit) {
        validateTitleGenre(genre);
        Pageable paging = PageRequest.of(offset, limit);

        String genreSearchKeyword = "%"+ TitleGenres.valueOf(genre.toUpperCase()).getGenreValue() +"%";

         List<TitleBasicsEntity> movies = titleBasicsRepository.
                 getTitleBasicsEntitiesByGenresOrderedByRating(genreSearchKeyword, paging);

        return movies.stream().map(dataModelConverter::toTopRatedMovieModel).collect(Collectors.toList());
    }

    void validateTitleGenre(String genre){
        if (!TitleGenres.contains(genre)){
            throw new InvalidQueryParamException(genre);
        }
    }
}
