package com.lunatech.imdb.components.movies.dao;

import com.lunatech.imdb.components.movies.dto.Cast;
import com.lunatech.imdb.components.names.dao.NameBasicsEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@Entity
@Table(name = "title_principals")
@Data
public class TitlePrincipalsEntity implements Serializable {

    @Id
    private String tconst;

    @Id
    private Integer ordering;

    @Id
    private String nconst;

    private String category;

    private String job;

    private String characters;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nconst", referencedColumnName = "nconst")
    private NameBasicsEntity nameBasics;

    public Cast toModel() {
        return Cast.builder()
                .name(nameBasics.getPrimaryName())
                .job(job)
                .characters(characters).build();
    }
}
