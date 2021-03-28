package com.lunatech.imdb.controllers;

import com.lunatech.imdb.common.constants.ApiConstants;
import com.lunatech.imdb.common.enums.TitleGenres;
import com.lunatech.imdb.components.movies.dto.Movie;
import com.lunatech.imdb.components.movies.dto.TopRatedMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.liquibase.change-log=classpath:/liquibase/master-test.xml")
class MoviesControllerComponentTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URI = "/imdb/movies";

    @Test
    @Sql("classpath:/liquibase/data/clear-data.sql")
    @Sql("classpath:/liquibase/data/add-movies-data.sql")
    void whenGetMoviesWithValidRequestThenSuccessWith_Status_200() {

        String uriPostfix = "/?"+ ApiConstants.PRIMARY_TITLE +"=fast&&"+ApiConstants.ORIGINAL_TITLE+"=fast";

        ResponseEntity<Movie[]> response = restTemplate.getForEntity(BASE_URI + uriPostfix, Movie[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()[0].getPrimaryName()).isEqualTo("fast");
        assertThat(response.getBody()[1].getPrimaryName()).isEqualTo("fast");
        assertThat(response.getBody()[0].getCrew().getDirectors()).hasSize(1);
        assertThat(response.getBody()[0].getCast()).hasSize(1);
        assertThat(response.getBody()[1].getCrew().getWriters()).hasSize(2);
        assertThat(response.getBody()[1].getCast()).hasSize(1);
    }

    @Test
    @Sql("classpath:/liquibase/data/clear-data.sql")
    @Sql("classpath:/liquibase/data/add-movies-data.sql")
    void whenGetTopRatedMoviesWithValidRequestThenSuccessWith_Status_200() {

        String uriPostfix = "/topRated?"+ ApiConstants.GENRE +"="+ TitleGenres.SPORT.getGenreValue();

        ResponseEntity<TopRatedMovie[]> response = restTemplate.getForEntity(BASE_URI + uriPostfix, TopRatedMovie[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()[0].getId()).isEqualTo("t157");
        assertThat(response.getBody()[0].getPrimaryName()).isEqualTo("test");
        assertThat(response.getBody()[0].getRating()).isEqualTo(9.5);
        assertThat(response.getBody()[0].getVotes()).isEqualTo(6);

        assertThat(response.getBody()[1].getId()).isEqualTo("t156");
        assertThat(response.getBody()[1].getPrimaryName()).isEqualTo("fast");
        assertThat(response.getBody()[1].getRating()).isEqualTo(9.5);
        assertThat(response.getBody()[1].getVotes()).isEqualTo(5);
    }

}
