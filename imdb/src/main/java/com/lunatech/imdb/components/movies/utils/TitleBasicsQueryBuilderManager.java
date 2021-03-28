package com.lunatech.imdb.components.movies.utils;

import com.lunatech.imdb.common.enums.TitleTypes;
import com.lunatech.imdb.components.movies.dao.TitleBasicsEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


import static org.springframework.data.jpa.domain.Specification.where;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */

@Component
public class TitleBasicsQueryBuilderManager {

    public Specification<TitleBasicsEntity> prepareGetMoviesQuery(TitleTypes titleTypes, String primaryTitle, String originalTitle) {


        Specification<TitleBasicsEntity> query =
                where(TitleBasicsSpecifications.filterOnType(titleTypes))
                        .and(where(TitleBasicsSpecifications.filterOnPrimaryTitle(primaryTitle))
                                .or(TitleBasicsSpecifications.filterOnOriginalTitle(originalTitle)));

        return query;
    }

}
