package com.lunatech.imdb.components.names;

import com.lunatech.imdb.components.movies.dto.Crew;
import com.lunatech.imdb.components.names.dto.NameDetails;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
public interface NamesService {

    NameDetails getNameDetails(String id);
}
