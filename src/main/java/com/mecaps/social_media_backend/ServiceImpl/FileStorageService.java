package com.mecaps.social_media_backend.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class FileStorageService {
    private final String BASE_UPLOAD_PATH = System.getProperty("user.dir") + "/uploads/";

    public String saveFile(MultipartFile file, String folder) {

        try {
            if (file == null || file.isEmpty()) return null;

            // Validate file type
            validateFileType(file);

            // Validate size (Instagram-like limits)
            validateFileSize(file);

            // Build folder structure
            String datePath = LocalDate.now().toString(); // 2025-01-20
            String uploadDir = BASE_UPLOAD_PATH + folder + "/" + datePath + "/";
            File dir = new File(uploadDir);

            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + uploadDir);
            }

            // Clean filename
            String safeName = cleanFileName(file.getOriginalFilename());

            // Create unique file name
            String finalName = UUID.randomUUID() + "_" + safeName;

            File destination = new File(uploadDir + finalName);

            // Save the actual file
            file.transferTo(destination);

            // Return a public-accessible path
            return "/uploads/" + folder + "/" + datePath + "/" + finalName;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }

    private void validateFileType(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null) throw new RuntimeException("Unknown file type");

        // Allow only images and videos
        if (contentType.startsWith("image/") || contentType.startsWith("video/")) {
            return; // valid file
        }

        throw new RuntimeException("Unsupported file type: " + contentType);
    }

    private void validateFileSize(MultipartFile file) {
        long sizeMB = file.getSize() / (1024 * 1024);

        if (file.getContentType().startsWith("image/") && sizeMB > 10) {
            throw new RuntimeException("Image too large (max 10MB)");
        }

        if (file.getContentType().startsWith("video/") && sizeMB > 200) {
            throw new RuntimeException("Video too large (max 200MB)");
        }
    }

    private String cleanFileName(String name) {
        if (name == null) return "file";

        // Normalize name: remove spaces, unicode, repeated dots
        name = name.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

        // Ensure extension exists
        if (!name.contains(".")) name += ".dat";

        return name;
    }
}
