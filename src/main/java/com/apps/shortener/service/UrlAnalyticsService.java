package com.apps.shortener.service;

import com.apps.shortener.documents.UrlMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


public interface UrlAnalyticsService {

    UrlMapping fetchStatistics_todo(String key);


    void logUrlAnalytics(String key, String userAgentString);
}
