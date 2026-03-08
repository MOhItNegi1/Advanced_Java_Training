package com.example.controller;

import com.example.dto.MaterialResponseDTO;
import com.example.entity.CourseMaterial;
import com.example.service.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class CourseMaterialController {

    @Autowired
    private CourseMaterialService materialService;


    @PostMapping(value="/upload", consumes = "multipart/form-data")
    public MaterialResponseDTO uploadMaterial(
            @RequestParam Long courseId,
            @RequestParam String title,
            @RequestParam MultipartFile file
    ) throws IOException {

        return materialService.uploadMaterial(courseId, file, title);
    }


    @GetMapping("/course/{courseId}")
    public List<CourseMaterial> getMaterialsByCourse(@PathVariable Long courseId){
        return materialService.getMaterialsByCourse(courseId);
    }


    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadMaterial(@PathVariable Long id){
        return materialService.downloadMaterial(id);
    }

}