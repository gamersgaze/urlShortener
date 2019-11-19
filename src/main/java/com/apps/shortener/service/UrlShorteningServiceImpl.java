package com.apps.shortener.service;

import com.apps.shortener.controller.request.UrlShorteningRequest;
import com.apps.shortener.documents.UrlMapping;
import com.apps.shortener.exception.InvalidUrlException;
import com.apps.shortener.generator.KeyGenerator;
import com.apps.shortener.repository.UrlMappingCacheRepository;
import com.apps.shortener.repository.UrlMappingMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.apps.shortener.utils.Utils.isUrlValid;
import static java.util.Objects.isNull;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShorteningServiceImpl.class);

    //url property is set in property file
    @Value("${url}")
    private String targetUrl;

    @Autowired
    private KeyGenerator keyGenerator;

    @Autowired
    private UrlMappingMongoRepository urlMappingRepository;

    @Autowired
    private UrlMappingCacheRepository urlMappingCacheRepository;

    @Autowired
    private UrlAnalyticsService urlAnalyticsService;

    @Override
    public String shortenUrl(UrlShorteningRequest request) {
        String originalUrl = request.getOriginalUrl();

        //validate the url
        if (!isUrlValid(originalUrl)) {
            throw new InvalidUrlException("Url is invalid");
        }

        //generate unique key
        String key = generateKey();
        //save mapping
        saveUrlMapping(key, originalUrl);
        //build short url by appending the key at the end
        return buildShortUrl(key);
    }

    @Override
    public String resolveOriginalUrl(String key, HttpServletRequest request){
        String originalUrl=urlMappingCacheRepository.fetchOriginalUrlByKey(key);

        //if short key is invalid
        if(isNull(originalUrl)){
            throw new InvalidUrlException("Url is invalid");
        }

        //asynchronous call
        storeAnalytics(key,request);

        return originalUrl;
    }

    /**
     *  call Asynchronous Analytics Service to Store data such as browser, OS and number of hits for url
     *  this service is Asynchronous because , the performance of the redirect should not get affected
     *  while request statistics is being collected and stored in database
     */
    private void storeAnalytics(String key, HttpServletRequest request) {
        String userAgentString = request.getHeader("User-Agent");
        urlAnalyticsService.logUrlAnalytics(key, userAgentString);
    }

    private String generateKey() {
        return keyGenerator.generateKey();
    }

    /**
     * Url mapping is saved in database and redis cache
     */
    private void saveUrlMapping(String key, String originalUrl) {
        UrlMapping urlMapping = new UrlMapping(key, originalUrl);
        //save in database
        urlMappingRepository.save(urlMapping);
        //save in redis cache
        urlMappingCacheRepository.save(key,originalUrl);
    }


    private String buildShortUrl(String key) {
        return new StringBuilder().append(targetUrl).append(key).toString();
    }
}
