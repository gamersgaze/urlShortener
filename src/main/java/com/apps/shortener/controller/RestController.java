package com.apps.shortener.controller;

import com.apps.shortener.controller.request.UrlShorteningRequest;
import com.apps.shortener.service.UrlAnalyticsService;
import com.apps.shortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@Controller
public class RestController {

    @Autowired
    UrlShorteningService urlShorteningService;

    @Autowired
    UrlAnalyticsService urlAnalyticsService;

    /*
     * Convert original Url into shorten Url and return shorten Url as response
     */
    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@Valid @RequestBody UrlShorteningRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlShorteningService.shortenUrl(request));
    }

    /*
     *  Resolve the original Url into Original Url
     */
    @GetMapping("/{key}")
    public ResponseEntity<?> resolveShortUrl(@PathVariable("key") String key, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create(urlShorteningService.resolveOriginalUrl(key, request)))
                .build();
    }

    /*
     * Analytics API
     */

    /*
     *  this API will fetch the statistics of how many time shortUrl is viewed and from which browser and operating system
     ***** sample response
     * {
           "id": "rLHWfKk",
            "url": "https://www.google.com",
            "browserHits": {
                "Unknown": 1,
                "Chrome": 5
                "FireFox":3
            },
            "operatingSystemHits": {
                "Unknown": 5,
                "Windows 10": 4
            },
            "totalHits": 9
        }
     */

    @GetMapping("/{key}/stats")
    public ResponseEntity<?> fetchStatistics(@PathVariable("key") String key) {
        return ResponseEntity.status(HttpStatus.OK).body(urlAnalyticsService.fetchStatistics(key));
    }
}
