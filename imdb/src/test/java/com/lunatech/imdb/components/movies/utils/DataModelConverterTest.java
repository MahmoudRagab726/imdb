package com.lunatech.imdb.components.movies.utils;

import com.lunatech.imdb.common.error.exceptions.NameNotFoundException;
import com.lunatech.imdb.components.movies.dto.CrewMember;
import com.lunatech.imdb.components.names.NamesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/7/2021 by OLE
 */

@ExtendWith(SpringExtension.class)
class DataModelConverterTest {

    @InjectMocks
    private DataModelConverter dataModelConverter;

    @Mock
    private NamesServiceImpl namesService;

    @Test
    void whenGetCrewMembersDetailsThatNotExistThen_ReturnEmptyList() {
        String id = "n14";
        given(namesService.getNameDetails(id)).willThrow(NameNotFoundException.class);
        List<CrewMember> result = dataModelConverter.getCrewMembersNameDetails(Collections.singletonList(id));
        assertThat(result).isEmpty();
    }

    @Test
    void whenGetCrewMembersDetailsThatExistThen_ReturnCrewMemberDetails() {
        String id = "n14";

        given(namesService.getNameDetails(id)).willThrow(NameNotFoundException.class);
        List<CrewMember> result = dataModelConverter.getCrewMembersNameDetails(Collections.singletonList(id));
        assertThat(result).isEmpty();
    }
}
