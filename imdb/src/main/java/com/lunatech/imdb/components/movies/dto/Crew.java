package com.lunatech.imdb.components.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crew implements Serializable {
    private List<CrewMember> directors;

    private List<CrewMember> writers;
}
