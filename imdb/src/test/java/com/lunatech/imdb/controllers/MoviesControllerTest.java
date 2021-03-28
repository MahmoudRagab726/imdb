package com.lunatech.imdb.controllers;

import com.lunatech.imdb.common.constants.ApiConstants;
import com.lunatech.imdb.common.enums.TitleGenres;
import com.lunatech.imdb.common.error.codes.ApiErrorCode;
import com.lunatech.imdb.common.error.exceptions.InvalidQueryParamException;
import com.lunatech.imdb.components.movies.MoviesServiceImpl;
import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MoviesController.class)
class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviesServiceImpl moviesService;

    private final String BASE_URI = "/imdb/movies";


    @Nested
    @DisplayName("Get Movies Tests")
    class GetMovies {
        @Test
        void whenGetMoviesWithInvalidQueryParamValue_String_offset_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String offset = "invalid";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + "/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset)
                            .param(ApiConstants.PRIMARY_TITLE, "test"))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetMoviesWithInvalidQueryParamValue_exceedAllowedValue_101_limit_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String limit = "101";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + "/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.LIMIT, limit)
                            .param(ApiConstants.PRIMARY_TITLE, "test"))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetMoviesWithInvalidQueryParamValue_exceedAllowedValue_Min1_offset_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String offset = "-1";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + "/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetMoviesWith_ValidRequest_ThenSuccessWithStatus_200() throws Exception {
            String uriPostfix = "/";
            String genre = TitleGenres.DRAMA.getGenreValue();
            Movie movie = Movie.builder()
                    .id("nod56")
                    .primaryName("PName")
                    .originalName("OName")
                    .genres(Collections.singletonList(genre)).build();
            given(moviesService.getMovies(null, null,0, 100)).willReturn(Collections.singletonList(movie));
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.GENRE, genre))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(movie.getId())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].primaryName", is(movie.getPrimaryName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].originalName", is(movie.getOriginalName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genres.[0]", is(movie.getGenres().get(0))));
        }
    }


    @Nested
    @DisplayName("Get Top Rated Movies By Genre Tests")
    class GetTopRatedMovies {
        @Test
        void whenGetTopRatedMoviesWithInvalidQueryParamValue_String_offset_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String offset = "test";
            String uriPostfix = "/topRated";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset)
                            .param(ApiConstants.GENRE, TitleGenres.ACTION.getGenreValue()))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetTopRatedMoviesWithInvalidQueryParamValue_exceedAllowedValue_101_limit_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String limit = "101";
            String uriPostfix = "/topRated";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.LIMIT, limit)
                            .param(ApiConstants.GENRE, TitleGenres.ACTION.getGenreValue()))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetTopRatedMoviesWithInvalidQueryParamValue_exceedAllowedValue_Min1_offset_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String offset = "-1";
            String uriPostfix = "/topRated";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset)
                            .param(ApiConstants.GENRE, TitleGenres.ACTION.getGenreValue()))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetTopRatedMoviesWithoutSendingRequiredQueryParam_genre_ThenFailAndReturn_400_MISSING_QUERY_PARAM() throws Exception {
            String offset = "0";
            String uriPostfix = "/topRated";
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.MISSING_QUERY_PARAM.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.MISSING_QUERY_PARAM.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetTopRatedMoviesWithInvalidParam_genre_ThenFailAndReturn_400_INVALID_QUERY_PARAM_VALUE() throws Exception {
            String offset = "0";
            String genre = "invalid";
            String uriPostfix = "/topRated";
            given(moviesService.getTopRatedMovies(genre,  0, 100)).willThrow(InvalidQueryParamException.class);
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset)
                            .param(ApiConstants.GENRE, genre))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.getCode())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_QUERY_PARAM_VALUE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
        }

        @Test
        void whenGetTopRatedMoviesWith_ValidRequest_ThenSuccessWithStatus_200() throws Exception {
            String offset = "0";
            String uriPostfix = "/topRated";
            String genre = TitleGenres.DRAMA.getGenreValue();
            TopRatedMovie movie = TopRatedMovie.builder()
                    .id("nod55")
                    .primaryName("PName")
                    .genres(Collections.singletonList(genre)).build();
            given(moviesService.getTopRatedMovies(genre, 0, 100)).willReturn(Collections.singletonList(movie));
            mockMvc
                    .perform(MockMvcRequestBuilders.get(BASE_URI + uriPostfix)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param(ApiConstants.OFFSET, offset)
                            .param(ApiConstants.GENRE, genre))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(movie.getId())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].primaryName", is(movie.getPrimaryName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genres.[0]", is(movie.getGenres().get(0))));
        }

    }
}
