package com.example.todoapi.controller.advice;

import com.example.todoapi.model.ResourseNotFoundError;
import com.example.todoapi.service.task.TaskEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourseNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException ex) {
        var error = new ResourseNotFoundError();
        error.setDetail(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}
