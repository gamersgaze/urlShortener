package com.apps.shortener.utils;

import java.util.Locale;

public class Constants {

    //redis counter
    public static final String REDIS_COUNTER_KEY = "urlCounter";
    public static final Long COUNTER_INITIAL_VALUE = 1000000000000L;

    //redis cache key
    public static final String URL_MAPPING_CACHE_KEY = "urlMapping";

    //internationalization
    public static final Locale DEFAULT_LOCALE_FALLBACK = Locale.US;
}
