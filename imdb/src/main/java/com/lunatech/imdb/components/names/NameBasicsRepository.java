package com.lunatech.imdb.components.names;

import com.lunatech.imdb.components.names.dao.NameBasicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@Repository
interface NameBasicsRepository extends JpaRepository<NameBasicsEntity, String> {
    Optional<NameBasicsEntity> findByNconst(String id);
}
