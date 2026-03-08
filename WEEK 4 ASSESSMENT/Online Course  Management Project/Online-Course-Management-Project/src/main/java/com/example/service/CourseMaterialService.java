package com.example.service;

import com.example.dto.MaterialResponseDTO;
import com.example.entity.Course;
import com.example.entity.CourseMaterial;
import com.example.repository.CourseMaterialRepository;
import com.example.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseMaterialService {

    @Autowired
    private CourseMaterialRepository materialRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final String UPLOAD_DIR = "uploads";

    public MaterialResponseDTO uploadMaterial(Long courseId, MultipartFile file, String title) throws IOException {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Create uploads folder if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save file
        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save material in DB
        CourseMaterial material = new CourseMaterial();
        material.setTitle(title);
        material.setFileName(file.getOriginalFilename());
        material.setFileType(file.getContentType());
        material.setFileUrl(filePath.toString());
        material.setUploadDate(LocalDateTime.now());
        material.setCourse(course);

        CourseMaterial saved = materialRepository.save(material);

        // Response DTO
        MaterialResponseDTO response = new MaterialResponseDTO();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setFileName(saved.getFileName());
        response.setFileType(saved.getFileType());
        response.setFileUrl(saved.getFileUrl());
        response.setUploadDate(saved.getUploadDate());

        return response;
    }

    public List<CourseMaterial> getMaterialsByCourse(Long courseId){

        return materialRepository.findByCourseId(courseId);
    }

    public ResponseEntity<Resource> downloadMaterial(Long id) {

        CourseMaterial material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        Path path = Paths.get(material.getFileUrl());

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (Exception e) {
            throw new RuntimeException("File not found");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + material.getFileName() + "\"")
                .body(resource);
    }
}