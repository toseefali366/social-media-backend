package com.mecaps.social_media_backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
private LocalDateTime timestamp;
private String message;
private int status;
private String path;
private String exception;
}
