package com.lunatech.imdb.controllers;

import com.lunatech.imdb.common.constants.ApiConstants;
import com.lunatech.imdb.common.enums.TitleGenres;
import com.lunatech.imdb.common.error.ApiError;
import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
public interface MoviesControllerApi {

    @ApiOperation(value = "List the movies of imdb dataset filtered by [primaryTitle, originalTitle]", response = Movie.class, responseContainer = "List")
    @ApiResponses({@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The movies have been found", response = Movie.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Some parameters are missing or invalid", response = ApiError.class)})
    List<Movie> getMovies(
            @ApiParam(value = "The primary title of the movie") String primaryTitle,
            @ApiParam(value = "The original title of the movie") String originalTitle,
            @ApiParam(value = "The index of the first element to retrieve. Zero is the first element of the collection") @Min(ApiConstants.MIN_OFFSET_FILTER) Integer offset,
            @ApiParam(value = "The maximum number of elements to retrieve (capped at " + ApiConstants.MAX_LIMIT_FILTER + ")") @Min(ApiConstants.MIN_LIMIT_FILTER) @Max(ApiConstants.MAX_LIMIT_FILTER) Integer limit);


    @ApiOperation(value = "List the top rated movies of imdb dataset filtered by [genres]", response = TopRatedMovie.class, responseContainer = "List")
    @ApiResponses({@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The movies have been found", response = TopRatedMovie.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Some parameters are missing or invalid", response = ApiError.class)})
    List<TopRatedMovie> getTopRatedMovies(
            @ApiParam(value = "The genre filter of the movies, it accept only values ["+ TitleGenres.VALUES_LIST +"] only.", required = true) String genre,
            @ApiParam(value = "The index of the first element to retrieve. Zero is the first element of the collection") @Min(ApiConstants.MIN_OFFSET_FILTER) Integer offset,
            @ApiParam(value = "The maximum number of elements to retrieve (capped at " + ApiConstants.MAX_LIMIT_FILTER + ")") @Min(ApiConstants.MIN_LIMIT_FILTER) @Max(ApiConstants.MAX_LIMIT_FILTER) Integer limit);

}
