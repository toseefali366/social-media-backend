package com.mecaps.social_media_backend.Validations;

import com.mecaps.social_media_backend.Entity.Comment;
import com.mecaps.social_media_backend.Entity.Post;
import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.*;
import com.mecaps.social_media_backend.Repository.CommentRepository;
import com.mecaps.social_media_backend.Repository.PostRepository;
import com.mecaps.social_media_backend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.internal.Abstract;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class Validation  {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
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

    public Post getPostById(Long postId) {

        log.info("Fetching post with id: {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with id: {}", postId);
                    return new RuntimeException("Post with id " + postId + " not found");
                });
        log.info("Post fetched successfully with id: {}", post.getId());
        return post;
    }

    public User getUserById(Long userId) {
        log.info("Fetching user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", userId);
                    return new UserNotFoundException("User with id " + userId + " not found");
                });
        log.info("User fetched successfully with id: {}", userId);
        return user;
    }

    public Comment getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    log.error("Comment with id {} is not present", commentId);
                    throw new RuntimeException("Comment not found");
                });
        log.info("Comment fetched successfully with id: {}", comment.getId());
        return comment;
    }

    public User getReceiverById(Long receiverId) {
        User user = userRepository.findById(receiverId).orElseThrow(() -> {
            log.error("Receiver with this id {} is not present", receiverId);
            throw new UserNotFoundException("Recever not found");
        });
        log.info("User fetched successfully with id: {}", user.getId());
        return user;
    }
}

