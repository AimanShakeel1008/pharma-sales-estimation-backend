package com.our_pharma_corp.pharma_sales_estimation_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileUploadService {

    private final S3Client s3Client;

    public FileUploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public void uploadToS3(MultipartFile file, String quarter) throws IOException {
        String key = "master_data_" + quarter + ".zip";

        // Convert multipart to temp file
        Path tempFile = Files.createTempFile("upload-", ".zip");
        file.transferTo(tempFile.toFile());

        // Upload to S3
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                tempFile
        );

        Files.deleteIfExists(tempFile); // Clean up temp file
    }
}

