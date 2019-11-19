package com.apps.shortener.repository;

public interface UrlMappingCacheRepository {
    void save(String key, String originalUrl);

    String fetchOriginalUrlByKey(String key);
}
