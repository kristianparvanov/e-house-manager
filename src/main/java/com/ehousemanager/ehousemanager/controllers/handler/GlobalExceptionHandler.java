package com.ehousemanager.ehousemanager.controllers.handler;

import com.ehousemanager.ehousemanager.dtos.ErrorResponseDto;
import com.ehousemanager.ehousemanager.exceptions.BuildingException;
import com.ehousemanager.ehousemanager.exceptions.CompanyException;
import com.ehousemanager.ehousemanager.exceptions.EmployeeException;
import com.ehousemanager.ehousemanager.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> errorList = exception
                .getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        log.error(exception.getMessage());

        return new ErrorResponseDto(errorList.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CompanyException.class, EmployeeException.class, BuildingException.class, UserException.class})
    public ErrorResponseDto handleBadRequestExceptions(RuntimeException exception) {
        log.error(exception.getMessage());

        return new ErrorResponseDto(exception.getMessage());
    }
}
