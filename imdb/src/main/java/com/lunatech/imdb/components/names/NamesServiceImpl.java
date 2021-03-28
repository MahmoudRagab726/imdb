package com.lunatech.imdb.components.names;

import com.lunatech.imdb.common.constants.CacheNames;
import com.lunatech.imdb.common.error.exceptions.NameNotFoundException;
import com.lunatech.imdb.components.names.dao.NameBasicsEntity;
import com.lunatech.imdb.components.names.dto.NameDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
@Service
@Slf4j
public class NamesServiceImpl implements NamesService {
    private final NameBasicsRepository nameBasicsRepository;

    @Autowired
    public NamesServiceImpl(NameBasicsRepository nameBasicsRepository) {
        this.nameBasicsRepository = nameBasicsRepository;
    }

    @Override
    @Cacheable(value = CacheNames.NAME_BASICS_DETAILS, key = "#id")
    public NameDetails getNameDetails(String id) {
        return nameBasicsRepository.findByNconst(id).map(NameBasicsEntity::toModel)
                .orElseThrow(() -> new NameNotFoundException(id));
    }
}
