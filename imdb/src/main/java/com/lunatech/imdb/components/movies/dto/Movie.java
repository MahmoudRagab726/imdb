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
public class Movie implements Serializable {
    private String id;

    private String primaryName;

    private String originalName;

    private String type;

    private List<String> genres;

    private List<Cast> cast;

    private Crew crew;
}
