package com.apps.shortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void save(String key, String originalUrl) {
        hashOperations.put(URL_MAPPING_CACHE_KEY, key, originalUrl);
    }

    @Override
    public String fetchOriginalUrlByKey(String key) {
        return hashOperations.get(URL_MAPPING_CACHE_KEY, key);
    }
}
