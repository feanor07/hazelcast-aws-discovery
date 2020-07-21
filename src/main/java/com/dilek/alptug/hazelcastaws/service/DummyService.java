package com.dilek.alptug.hazelcastaws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DummyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Cacheable("dummy")
    public long generateRandomLong() {
        return System.currentTimeMillis();
    }

    @CacheEvict("dummy")
    public String clearRandomlyGeneratedLong() {
        logger.info("Clearing cache so next time a new value will be generated");
        return "Cache cleared";
    }
}
