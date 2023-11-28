package com.ajlouni.clusteredDataWarehouse.controller;

import com.ajlouni.clusteredDataWarehouse.exception.CustomErrorAttributes;
import com.ajlouni.clusteredDataWarehouse.exception.DuplicateDealDataException;
import com.ajlouni.clusteredDataWarehouse.exception.InvalidDealDataException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(InvalidDealDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleInvalidDealDataException(InvalidDealDataException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.MESSAGE));
        errorAttributes.put("error", "Bad Request");
        errorAttributes.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(errorAttributes);
    }

    @ExceptionHandler(DuplicateDealDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, Object>> handleDuplicateDealDataException(DuplicateDealDataException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.MESSAGE));
        errorAttributes.put("error", "Conflict");
        errorAttributes.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorAttributes);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleUnexpectedException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.MESSAGE));
        errorAttributes.put("error", "Internal Server Error");
        errorAttributes.put("message", "Unexpected error occurred");
        return ResponseEntity.internalServerError().body(errorAttributes);
    }

    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new CustomErrorAttributes();
    }
}