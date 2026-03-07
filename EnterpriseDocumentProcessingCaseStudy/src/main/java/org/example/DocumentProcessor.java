package org.example;

import org.springframework.stereotype.Component;

@Component
public interface DocumentProcessor {

    public void processDocument( String DocumentName);
}
