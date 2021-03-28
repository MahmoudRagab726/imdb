package com.lunatech.imdb.components.movies;

import com.lunatech.imdb.common.error.exceptions.InvalidQueryParamException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/7/2021 by OLE
 */

@ExtendWith(SpringExtension.class)
class MoviesServiceTest {

    @InjectMocks
    private MoviesServiceImpl moviesService;

    @Test
    void whenValidateTitleGenreWithInvalidGenreThenThrow_InvalidQueryParamException() {
        assertThrows(InvalidQueryParamException.class,()-> moviesService.validateTitleGenre("invalid"));

    }
}
