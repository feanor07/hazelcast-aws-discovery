package com.dilek.alptug.hazelcastaws.controller;

import com.dilek.alptug.hazelcastaws.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @Autowired private DummyService dummyService;

    @Value("${app.tag.enabled}")
    private boolean tagEnabled;

    @Value("${app.sg.enabled}")
    private boolean sgEnabled;

    @GetMapping("/")
    public String getMessage() {
        return "Hazelcast discovery method: " + getFilteringIdentifier() + ", cached value: " + dummyService.generateRandomLong();
    }

    @GetMapping("/clear-cache")
    public String clearCache() {
        return dummyService.clearRandomlyGeneratedLong();
    }

    private String getFilteringIdentifier() {
        return tagEnabled ? "Tag Key-Value Filtering" : sgEnabled ? "Security Group Filtering" : "Multi-casting";
    }
}
