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
@Table(name = "title_ratings")
@Data
public class TitleRatingEntity {
    @Id
    private String tconst;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "num_votes")
    private Integer numVotes;


}
