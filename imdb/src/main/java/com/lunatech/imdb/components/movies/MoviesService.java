package com.lunatech.imdb.components.movies;

import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;

import java.util.List;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
public interface MoviesService {
    List<Movie> getMovies(String primaryTitle, String originalTitle, Integer offset, Integer limit);

    List<TopRatedMovie> getTopRatedMovies(String genre, Integer offset, Integer limit);
}
