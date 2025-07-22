package com.javalord.niit_web_service.niit_web_service.errorhandler;

import com.javalord.niit_web_service.niit_web_service.model.errror.Error;
import com.javalord.niit_web_service.niit_web_service.model.exception.NoSuchResourceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseEntity<?> handleNoSuchResourceException(NoSuchResourceException ex) {
        Error error = new Error();
        error.setMesssage(ex.getMessage());
        error.setDate(new Date());

        return ResponseEntity.status(404).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errorMessages = new ArrayList<>();
        List<ObjectError> allErrors = ex
                .getAllErrors();
      for (ObjectError o : allErrors) {
          errorMessages.add(o.getDefaultMessage());
      }

        return ResponseEntity.badRequest().body(errorMessages);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.badRequest().body("The request body must be in correct format!");
    }
}
