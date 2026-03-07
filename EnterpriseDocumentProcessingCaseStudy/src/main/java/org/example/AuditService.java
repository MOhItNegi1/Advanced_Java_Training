package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AuditService {

    @PostConstruct
    public void init() {
        System.out.println("Audit Service Initialized");
    }

    public void log(String msg) {
        System.out.println("AUDIT: " + msg);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Audit Service Destroyed");
    }
}
