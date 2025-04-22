package com.our_pharma_corp.pharma_sales_estimation_backend.controllers;


import com.our_pharma_corp.pharma_sales_estimation_backend.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // For React frontend
public class EstimationController {

    private final FileUploadService fileUploadService;

    public EstimationController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("quarter") String quarter) {
        try {
            fileUploadService.uploadToS3(file, quarter);
            return ResponseEntity.ok("✅ File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("❌ Upload failed: " + e.getMessage());
        }
    }
}

