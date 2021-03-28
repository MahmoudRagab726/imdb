package com.lunatech.imdb.controllers;

import com.lunatech.imdb.common.constants.ApiConstants;
import com.lunatech.imdb.components.movies.MoviesService;
import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@RestController
@RequestMapping("/imdb")
@Validated
public class MoviesController implements MoviesControllerApi{

    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getMovies(@RequestParam(name = ApiConstants.PRIMARY_TITLE, required = false) String primaryTitle,
                                 @RequestParam(name = ApiConstants.ORIGINAL_TITLE, required = false) String originalTitle,
                                 @RequestParam(name = ApiConstants.OFFSET, required = false, defaultValue = ApiConstants.MIN_OFFSET_FILTER+"") @Min(ApiConstants.MIN_OFFSET_FILTER) Integer offset,
                                 @RequestParam(name = ApiConstants.LIMIT, required = false, defaultValue = ApiConstants.MAX_LIMIT_FILTER+"") @Min(ApiConstants.MIN_LIMIT_FILTER) @Max(ApiConstants.MAX_LIMIT_FILTER) Integer limit) {

        return moviesService.getMovies(primaryTitle, originalTitle, offset, limit);
    }

    @Override
    @GetMapping("/movies/topRated")
    @ResponseStatus(HttpStatus.OK)
    public List<TopRatedMovie> getTopRatedMovies(@RequestParam(name = ApiConstants.GENRE) String genre,
                                                 @RequestParam(name = ApiConstants.OFFSET, required = false, defaultValue = ApiConstants.MIN_OFFSET_FILTER+"") @Min(ApiConstants.MIN_OFFSET_FILTER) Integer offset,
                                                 @RequestParam(name = ApiConstants.LIMIT, required = false, defaultValue = ApiConstants.MAX_LIMIT_FILTER+"") @Min(ApiConstants.MIN_LIMIT_FILTER) @Max(ApiConstants.MAX_LIMIT_FILTER) Integer limit) {

        return moviesService.getTopRatedMovies(genre, offset, limit);
    }
}
