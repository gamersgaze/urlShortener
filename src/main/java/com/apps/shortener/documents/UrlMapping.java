package com.apps.shortener.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Document
public class UrlMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String url;
    private Map<String, Long> browserHits=new HashMap<>();
    private Map<String, Long> operatingSystemHits =new HashMap<>();
    private Long totalHits;

    public UrlMapping() {

    }

    public UrlMapping(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Long> getBrowserHits() {
        return browserHits;
    }

    public void setBrowserHits(Map<String, Long> browserHits) {
        this.browserHits = browserHits;
    }

    public Map<String, Long> getOperatingSystemHits() {
        return operatingSystemHits;
    }

    public void setOperatingSystemHits(Map<String, Long> operatingSystemHits) {
        this.operatingSystemHits = operatingSystemHits;
    }

    public Long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Long totalHits) {
        this.totalHits = totalHits;
    }
}
