package com.mecaps.social_media_backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;   // When error happened
    private int status;                // HTTP status code
    private String error;              // Short error name (BAD_REQUEST)
    private String message;            // Human readable message
    private String path;               // API path
    private String errorCode;           // Custom error code (IMPORTANT)
}
