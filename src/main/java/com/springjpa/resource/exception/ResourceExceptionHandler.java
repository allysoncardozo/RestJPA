package com.springjpa.resource.exception;

import com.springjpa.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
        ApiError erro = new ApiError();
        int erroCod = HttpStatus.NOT_FOUND.value();
        erro.setCode(erroCod);
        erro.setDate(LocalDateTime.now());
        erro.setError(ex.getMessage());

        return ResponseEntity.status(erroCod).body(erro);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                    HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(p -> {
            erros.add(p.getDefaultMessage());
        });

        String message = ex.getMessage();
        ApiErrorList erroList = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now(), erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroList);
    }
}
