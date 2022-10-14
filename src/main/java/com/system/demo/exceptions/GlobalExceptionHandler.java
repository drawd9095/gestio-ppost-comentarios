package com.system.demo.exceptions;

import com.system.demo.dto.ErrorDeatilsDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotfoundException.class)
    public ResponseEntity<ErrorDeatilsDTO> manejarResourcesNoFoundException(
            ResourceNotfoundException exception, WebRequest webRequest){
                ErrorDeatilsDTO errorDeatilsDTO = new ErrorDeatilsDTO(
                        new Date(),exception.getMessage(),webRequest.getDescription(false));
                return new ResponseEntity<>(errorDeatilsDTO, HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(BlogappException.class)
    public ResponseEntity<ErrorDeatilsDTO> manejarBlogappException(
            BlogappException exception, WebRequest webRequest){
        ErrorDeatilsDTO errorDeatilsDTO = new ErrorDeatilsDTO(
                new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDeatilsDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDeatilsDTO> manejarGlobalException(
            Exception exception, WebRequest webRequest){
        ErrorDeatilsDTO errorDeatilsDTO = new ErrorDeatilsDTO(
                new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDeatilsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String > errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError)error).getField();

            String mensaje = error.getDefaultMessage();

            errores.put(nombreCampo,mensaje);
        });

        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
