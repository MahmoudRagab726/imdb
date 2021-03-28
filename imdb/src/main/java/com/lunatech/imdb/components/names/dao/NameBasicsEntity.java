package com.lunatech.imdb.components.names.dao;

import com.lunatech.imdb.components.names.dto.NameDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@Entity
@Table(name = "name_basics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameBasicsEntity {
    @Id
    private String nconst;

    @Column(name = "primary_name")
    private String primaryName;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @Column(name = "primary_profession")
    private String primaryProfession;

    @Column(name = "known_for_titles")
    private String knownForTitles;

    public NameDetails toModel(){
        return NameDetails.builder()
                .name(primaryName)
                .profession(primaryProfession)
                .birthYear(birthYear).build();
    }

}
