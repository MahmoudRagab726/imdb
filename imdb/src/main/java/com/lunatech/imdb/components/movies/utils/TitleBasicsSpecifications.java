package com.lunatech.imdb.components.movies.utils;

import com.lunatech.imdb.common.enums.TitleTypes;
import com.lunatech.imdb.components.movies.dao.TitleBasicsEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TitleBasicsSpecifications {

    public static Specification<TitleBasicsEntity> filterOnType(final TitleTypes titleTypes) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("titleType"), titleTypes.getNameValue());
    }

    public static Specification<TitleBasicsEntity> filterOnPrimaryTitle(final String primaryTitle) {
        if (primaryTitle == null || primaryTitle.isEmpty())
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("primaryTitle"), primaryTitle);
    }

    public static Specification<TitleBasicsEntity> filterOnOriginalTitle(final String originalTitle) {
        if (originalTitle == null || originalTitle.isEmpty())
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("originalTitle"), originalTitle);
    }

}
