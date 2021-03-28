package com.lunatech.imdb.components.movies;

import com.lunatech.imdb.components.movies.dao.TitleBasicsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@Repository
interface TitleBasicsRepository extends JpaRepository<TitleBasicsEntity, String> ,
        JpaSpecificationExecutor<TitleBasicsEntity> {

    @Query("SELECT t FROM TitleBasicsEntity t join TitleRatingEntity r ON r.tconst = t.tconst WHERE t.genres like:genre order by r.averageRating DESC, r.numVotes DESC")
    List<TitleBasicsEntity> getTitleBasicsEntitiesByGenresOrderedByRating(String genre, Pageable page);

}
