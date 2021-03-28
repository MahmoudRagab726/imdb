package com.lunatech.imdb.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/5/2021 by OLE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {

    public static final long MAX_LIMIT_FILTER = 100;

    public static final long MIN_OFFSET_FILTER = 0;

    public static final long MIN_LIMIT_FILTER = 1;

    public static final String GENRES_SPLITTER = ",";

    public static final String DIRECTORS_SPLITTER = ",";

    public static final String WRITERS_SPLITTER = ",";

    public static final String PRIMARY_TITLE = "primary-title";

    public static final String ORIGINAL_TITLE = "original-title";

    public static final String OFFSET = "offset";

    public static final String LIMIT = "limit";

    public static final String GENRE = "genre";



}
