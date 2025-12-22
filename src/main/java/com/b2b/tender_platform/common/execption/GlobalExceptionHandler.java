package com.b2b.tender_platform.common.execption;

import com.b2b.tender_platform.common.api.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> notFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,ex.getMessage(),null));
    }

    public ResponseEntity<ApiResponse<?>> generic(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false,ex.getMessage(),null));
    }
}
