package org.example;


import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class DocumentEngine {
    public static void main(String[]args){}

    private DocumentProcessor processor;

    @Autowired
    private StorageService storageService;

    private AuditService auditService;


    @Autowired
    public DocumentEngine(@Qualifier("xmlDocumentProcessor") DocumentProcessor processor) {
        this.processor = processor;
    }


    @Autowired
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    public void run(String doc) {
        auditService.log("Starting processing");
        processor.processDocument(doc);
        storageService.store(doc);
    }
}