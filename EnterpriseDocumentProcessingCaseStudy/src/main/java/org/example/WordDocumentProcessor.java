package org.example;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class WordDocumentProcessor implements DocumentProcessor{

    @Override
    public void processDocument(String DocumentName) {
        System.out.println(" Processing Word Document"+DocumentName);
    }
}
