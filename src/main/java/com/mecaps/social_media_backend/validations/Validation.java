package com.mecaps.social_media_backend.validations;

import com.mecaps.social_media_backend.entity.Comment;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.exception.FileNotUploadException;
import com.mecaps.social_media_backend.exception.FileSizeExceededException;
import com.mecaps.social_media_backend.exception.FileStorageException;
import com.mecaps.social_media_backend.exception.InvalidFileTypeException;
import com.mecaps.social_media_backend.repository.CommentRepository;
import com.mecaps.social_media_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class Validation  {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


private final String BASE_UPLOAD_PATH = System.getProperty("user.dir") + "/uploads/";

public String saveImage(MultipartFile file, String folder) {

    try {
        if (file == null || file.isEmpty()) return null;

        // Validate file type
        validateFileType(file);

        // Validate size
        validateFileSize(file);

        // Build folder structure
        String datePath = LocalDate.now().toString(); // 2025-01-20
        String uploadDir = BASE_UPLOAD_PATH + folder + "/" + datePath + "/";
        File dir = new File(uploadDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new FileStorageException("Failed to create directory: " + uploadDir);
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
        throw new FileNotUploadException("Failed to upload file: " + e.getMessage());
    }
}

private void validateFileType(MultipartFile file) {
    String contentType = file.getContentType();

    if (contentType == null) throw new RuntimeException("Unknown file type");

    // Allow only images and videos
    if (contentType.startsWith("image/") || contentType.startsWith("video/")) {
        return; // valid file
    }

    throw new InvalidFileTypeException("Unsupported file type: " + contentType);
}

private void validateFileSize(MultipartFile file) {
    long sizeMB = file.getSize() / (1024 * 1024);

    if (file.getContentType().startsWith("image/") && sizeMB > 10) {
        throw new FileSizeExceededException("Image too large (max 10MB)");
    }

    if (file.getContentType().startsWith("video/") && sizeMB > 200) {
        throw new FileSizeExceededException("Video too large (max 200MB)");
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

    public Post getPostFromDb(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> {
            log.error("post with id {} is not present", id);
            throw new RuntimeException("post not found");

        });

        log.info("post with id {} found",id);
        return post;
    }
    public Comment getCommentFromDb(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found with id: " + commentId));
    }


}
