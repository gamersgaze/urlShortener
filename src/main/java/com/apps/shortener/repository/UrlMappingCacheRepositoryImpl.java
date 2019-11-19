package com.apps.shortener.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.apps.shortener.utils.Constants.URL_MAPPING_CACHE_KEY;

@Component
public class UrlMappingCacheRepositoryImpl implements UrlMappingCacheRepository {

    /**
     * HashOperations will create a hashMap in redis which will store the mapping [ shortKey -> original URL]
     */

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOperations;

    /**
     * save mapping cache
     */
    @Override
    public void save(String key, String originalUrl) {
        hashOperations.put(URL_MAPPING_CACHE_KEY, key, originalUrl);
    }

    /**
     * fetch original url from cache
     */
    @Override
    public String fetchOriginalUrlByKey(String key) {
        return hashOperations.get(URL_MAPPING_CACHE_KEY, key);
    }
}
