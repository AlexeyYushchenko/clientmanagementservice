package ru.utlc.clientmanagementservice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ru.utlc.clientmanagementservice.constants.CacheNames.*;

@Configuration
@EnableCaching
public class CacheConfig {
    public CacheConfig() {
    }

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CLIENTS, CLIENT_STATUSES, BUSINESS_TYPES, INDUSTRY_TYPES);
    }
}
