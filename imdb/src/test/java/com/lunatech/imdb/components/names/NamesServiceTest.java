package com.lunatech.imdb.components.names;

import com.lunatech.imdb.common.error.exceptions.NameNotFoundException;
import com.lunatech.imdb.components.names.dao.NameBasicsEntity;
import com.lunatech.imdb.components.names.dto.NameDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/7/2021 by OLE
 */
@ExtendWith(SpringExtension.class)
class NamesServiceTest {

    @Mock
    private NameBasicsRepository nameBasicsRepository;

    @InjectMocks
    private NamesServiceImpl namesService;

    @Test
    void whenGetNameDetailsThatExist_ThenReturnNameDetailsSuccessfully() {
        String nConst = "n11";
        NameBasicsEntity nameBasicsEntity = NameBasicsEntity.builder()
                .primaryName("name").primaryProfession("actor").birthYear(1994).build();
        given(nameBasicsRepository.findByNconst(nConst)).willReturn(Optional.of(nameBasicsEntity));

        NameDetails result = namesService.getNameDetails(nConst);
        assertThat(result.getName()).isEqualTo(nameBasicsEntity.getPrimaryName());
        assertThat(result.getProfession()).isEqualTo(nameBasicsEntity.getPrimaryProfession());
        assertThat(result.getBirthYear()).isEqualTo(nameBasicsEntity.getBirthYear());

    }

    @Test
    void whenGetNameDetailsThatNotExist_ThenThrow_NameNotFoundException() {
        String nConst = "n12";
        given(nameBasicsRepository.findByNconst(nConst)).willReturn(Optional.empty());

        assertThrows(NameNotFoundException.class,()-> namesService.getNameDetails(nConst));
    }
}
