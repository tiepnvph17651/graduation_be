package com.example.demo.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {
    // Bắt lỗi MethodArgumentNotValidException khi validation không hợp lệ
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Lấy tất cả các thông báo lỗi (chỉ lấy phần message)
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage) // Chỉ lấy nội dung thông báo lỗi
                .collect(Collectors.toList());

        // Trả về danh sách thông báo lỗi
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
