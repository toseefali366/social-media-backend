package com.mecaps.social_media_backend.validations;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class Validation {
    public String saveImage(MultipartFile image, String folder) {

        try {
            if (image == null || image.isEmpty()) return null;

            // Use absolute path
            String uploadDir = System.getProperty("user.dir") + "/uploads/" + folder + "/";
            File dir = new File(uploadDir);

            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + uploadDir);
            }

            // Avoid unsafe filenames
            String original = image.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\ .\\-]", "_");

            String fileName = UUID.randomUUID() + "_" + original;
            File destination = new File(uploadDir + fileName);

            // Save file
            image.transferTo(destination);

            // Return relative path for frontend
            return "/uploads/" + folder + "/" + fileName;

        } catch (Exception e) {
            e.printStackTrace(); // <-- So you can see exact root cause
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }

    }
    public void deleteImage(String imagePath) {
        try {
            if (imagePath == null || imagePath.isBlank())
                return;

            String fullPath = System.getProperty("user.dir") + imagePath;
            File file = new File(fullPath);

            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.out.println("Failed to delete file: " + fullPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
