package com.musala.task.exceptionhandler;

import com.musala.task.dtos.ErrorMessageDto;
import com.musala.task.customexception.MaxTenDronesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxTenDronesException.class)
    public ResponseEntity<ErrorMessageDto> ReachedMaxTenDronesExceptionHandler(MaxTenDronesException ex, WebRequest request) {
        ErrorMessageDto message = new ErrorMessageDto(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                Collections.singletonList(ex.getMessage()),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorMessageDto> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessageDto message = new ErrorMessageDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                Collections.singletonList(ex.getMessage()),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> validationErrorsHandler(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ErrorMessageDto message = new ErrorMessageDto(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errors,
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}