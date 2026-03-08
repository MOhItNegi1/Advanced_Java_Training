package com.example.dto;



import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MaterialUploadDTO {

    private String title;

    private Long courseId;

    private MultipartFile file;

}