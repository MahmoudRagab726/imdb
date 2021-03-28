package com.lunatech.imdb.components.movies;

import com.lunatech.imdb.common.enums.TitleGenres;
import com.lunatech.imdb.components.movies.dao.TitleBasicsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author Mahmoud Sakr - mahmoud.sakr.ext@orange.com
 * @Created: @ 3/8/2021 by OLE
 */
@DataJpaTest
@TestPropertySource(properties = "spring.liquibase.change-log=classpath:/liquibase/master-test.xml")
class TitleBasicsRepositoryTest {

    private final TitleBasicsRepository titleBasicsRepository;

    @Autowired
    public TitleBasicsRepositoryTest(TitleBasicsRepository titleBasicsRepository) {
        this.titleBasicsRepository = titleBasicsRepository;
    }

    @Sql("classpath:/liquibase/data/add-movies-title-basic-repo-data.sql")
    @Test
    void whenGetTopRatedMoviesWithGenre_Sport_And_Limit_2_ThenReturnQueriedDataOrderedByRatingThenVotesSuccessfully() {
        int offset = 0;
        int limit = 2;
        String genre = TitleGenres.SPORT.getGenreValue();
        Pageable paging = PageRequest.of(offset, limit);

        List<TitleBasicsEntity> movies = titleBasicsRepository.getTitleBasicsEntitiesByGenresOrderedByRating(genre, paging);

        assertThat(movies).hasSize(2);
        assertThat(movies.get(0).getTconst()).isEqualTo("t157");
        assertThat(movies.get(1).getTconst()).isEqualTo("t156");
    }

    @Sql("classpath:/liquibase/data/add-movies-title-basic-repo-data.sql")
    @Test
    void whenGetTopRatedMoviesWithGenre_Sport_And_Offset_1_ThenReturnQueriedDataOrderedByRatingThenVotesSuccessfully() {
        int offset = 1;
        int limit = 2;
        String genre = TitleGenres.SPORT.getGenreValue();
        Pageable paging = PageRequest.of(offset, limit);

        List<TitleBasicsEntity> movies = titleBasicsRepository.getTitleBasicsEntitiesByGenresOrderedByRating(genre, paging);

        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getTconst()).isEqualTo("t158");
    }
}
