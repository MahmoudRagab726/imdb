package com.lunatech.imdb.common.enums;

import lombok.Getter;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
public enum TitleGenres {
    ACTION("Action"),
    COMEDY("Comedy"),
    HORROR("Horror"),
    DRAMA("Drama"),
    NEWS("News"),
    FAMILY("Family"),
    ROMANCE("Romance"),
    MUSIC("Music"),
    SPORT("Sport"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    MYSTERY("Mystery"),
    ADULT("Adult"),
    BIOGRAPHY("Biography"),
    WAR("War"),
    FANTASY("Fantasy"),
    ANIMATION("Animation"),
    WESTERN("Western"),
    SHORT("Short");

    @Getter
    private String genreValue;

    public static final String VALUES_LIST = "Action, Comedy ,Horror, Drama, News, Family, Romance, Music," +
            " Sport, Crime, Documentary, Mystery, Adult, Biography, War, Fantasy, Animation, Western, Short";

    TitleGenres(String genre) {
        this.genreValue = genre;
    }

    public static boolean contains(String value) {
        value = value.toUpperCase();
        try {
            valueOf(value);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
