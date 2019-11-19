package com.apps.shortener.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UrlShorteningRequest {

    String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
