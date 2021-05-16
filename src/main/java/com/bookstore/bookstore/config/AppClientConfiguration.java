package com.bookstore.bookstore.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppClientConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
