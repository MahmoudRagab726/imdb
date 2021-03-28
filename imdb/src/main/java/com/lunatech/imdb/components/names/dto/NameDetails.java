package com.lunatech.imdb.components.names.dto;

import com.lunatech.imdb.components.movies.dto.CrewMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameDetails implements Serializable {
    private String name;

    private String profession;

    private Integer birthYear;

    public CrewMember toCrewMemberModel() {
        return CrewMember.builder()
                .name(name)
                .profession(profession)
                .build();
    }
}
