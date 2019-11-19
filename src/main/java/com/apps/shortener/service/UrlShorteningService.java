package com.apps.shortener.service;

import com.apps.shortener.controller.request.UrlShorteningRequest;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface UrlShorteningService {
    String shortenUrl(UrlShorteningRequest request);

    String resolveOriginalUrl(String key, HttpServletRequest request);

}
