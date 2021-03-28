package com.lunatech.imdb.components.movies.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@Entity
@Table(name = "title_crew")
@Data
public class TitleCrewEntity {
    @Id
    private String tconst;

    @Column(name = "directors")
    private String directors;

    @Column(name = "writers")
    private String writers;

}
