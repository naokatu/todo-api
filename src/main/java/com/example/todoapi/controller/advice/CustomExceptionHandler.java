package com.example.todoapi.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.ResourseNotFoundError;
import com.example.todoapi.service.task.TaskEntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourseNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException ex) {
        var error = new ResourseNotFoundError();
        error.setDetail(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BadRequestError> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        var error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }
}
