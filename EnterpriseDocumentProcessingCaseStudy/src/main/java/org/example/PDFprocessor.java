package org.example;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class PDFprocessor implements DocumentProcessor {

    @Override
    public void processDocument(String DocumentName) {
        System.out.println("Processing PDF document" + DocumentName);
    }
}
