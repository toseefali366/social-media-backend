package com.mecaps.social_media_backend.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
@ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException
        (UserAlreadyExistException exception, HttpServletRequest request){
ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
        HttpStatus.CONFLICT.value(),
        exception.getMessage(),
        request.getRequestURI());

    return new  ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
}
@ExceptionHandler(UserAlreadyVerifiedException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyVerifiedException
        (UserAlreadyVerifiedException exception , HttpServletRequest request){
    ErrorResponse errorResponse = new ErrorResponse((LocalDateTime.now()),
            HttpStatus.CONTINUE.value(),
            exception.getMessage(),
            request.getRequestURI());
    return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
}
@ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentials exception,HttpServletRequest request){

    ErrorResponse errorResponse = new ErrorResponse((LocalDateTime.now()),
            HttpStatus.CONTINUE.value(),
            exception.getMessage(),
            request.getRequestURI());

    return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
}

}
