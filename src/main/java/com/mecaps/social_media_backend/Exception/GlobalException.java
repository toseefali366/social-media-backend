package com.mecaps.social_media_backend.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    /* ================= USER / AUTH ================= */

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException ex, HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.NOT_FOUND,
                "USER_NOT_FOUND",
                request
        );
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOtp(
            InvalidOtpException ex, HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.BAD_REQUEST,
                "INVALID_OTP",
                request
        );
    }

    /* ================= FILE VALIDATION ================= */

    @ExceptionHandler(FileNotUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileNotUploaded(
            FileNotUploadException ex, HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.BAD_REQUEST,
                "FILE_NOT_UPLOADED",
                request
        );
    }

    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFileType(
            InvalidFileTypeException ex, HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.BAD_REQUEST,
                "INVALID_FILE_TYPE",
                request
        );
    }

    @ExceptionHandler(FileSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleFileSizeExceeded(
            FileSizeExceededException ex, HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.PAYLOAD_TOO_LARGE,
                "FILE_SIZE_EXCEEDED",
                request
        );
    }

    /* ================= FILE STORAGE ================= */

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> handleFileStorageException(
            FileStorageException ex, HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "FILE_STORAGE_ERROR",
                request
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(
            UserAlreadyExistsException ex, HttpServletRequest request){
        return buildErrorResponse(
                ex,
                HttpStatus.CONFLICT,
                "USER_ALREADY_EXISTS",
                request
        );
    }

    /* ================= COMMON BUILDER ================= */

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception ex,
            HttpStatus status,
            String errorCode,
            HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.name(),
                ex.getMessage(),
                request.getRequestURI(),
                errorCode
        );

        return ResponseEntity.status(status).body(errorResponse);
    }
}
