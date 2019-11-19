package com.apps.shortener.service;

import com.apps.shortener.documents.UrlMapping;
import com.apps.shortener.exception.KeyNotFoundException;
import com.apps.shortener.repository.UrlMappingMongoRepository;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class UrlAnalyticsServiceImpl  implements UrlAnalyticsService {

    @Autowired
    private UrlMappingMongoRepository urlMappingRepository;


    @Override
    public UrlMapping fetchStatistics(String key){
        Optional<UrlMapping> urlMapping = urlMappingRepository.findById(key);
        if (urlMapping.isPresent()) {
            return urlMapping.get();
        }else{
            throw new KeyNotFoundException("key is invalid");
        }
    }

    @Override
    @Async
    public void logUrlAnalytics(String key, String userAgentString) {
        Optional<UrlMapping> urlMapping = urlMappingRepository.findById(key);

        //if key is found
        if (urlMapping.isPresent()) {

            if (isNotBlank(userAgentString)) {
                UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

                //save analytics stats
                storeStatistics(urlMapping.get(), userAgent);
            }
        }
    }

    private void storeStatistics(UrlMapping urlMapping, UserAgent userAgent) {
        setBrowserStats(userAgent, urlMapping);
        setOperatingSystemStats(userAgent, urlMapping);
        setTotalHits(urlMapping);
        urlMappingRepository.save(urlMapping);
    }

    private UrlMapping setBrowserStats(UserAgent userAgent, UrlMapping urlMapping) {
        String browserName = userAgent.getBrowser().getName();
        Long count = urlMapping.getBrowserHits().get(browserName);
        if (nonNull(count)) {
            urlMapping.getBrowserHits().put(browserName, ++count);
        } else {
            urlMapping.getBrowserHits().put(browserName, 1L);
        }
        return urlMapping;
    }

    private UrlMapping setOperatingSystemStats(UserAgent userAgent, UrlMapping urlMapping) {
        String operatingSystemName = userAgent.getOperatingSystem().getName();
        Long count = urlMapping.getOperatingSystemHits().get(operatingSystemName);
        if (nonNull(count)) {
            urlMapping.getOperatingSystemHits().put(operatingSystemName, ++count);
        } else {
            urlMapping.getOperatingSystemHits().put(operatingSystemName, 1L);
        }
        return urlMapping;
    }

    private UrlMapping setTotalHits(UrlMapping urlMapping) {
        Long totalHits = urlMapping.getTotalHits();
        if (nonNull(totalHits)) {
            urlMapping.setTotalHits(++totalHits);
        } else {
            urlMapping.setTotalHits(1L);
        }
        return urlMapping;
    }
}
