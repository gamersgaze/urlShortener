package com.apps.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Configuration
@EnableAsync
//@EnableMongoRepositories(basePackages = "com.apps.shortener.documents")
//@EnableMongoRepositories({ "com.apps.shortener.*.repository", "com.apps.shortener.*.*.repository" })
//@EnableJpaRepositories({ "com.apps.shortener.*.repository", "com.apps.shortener.*.*.repository" })
public class ShortenerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShortenerApplication.class, args);

    }
}
