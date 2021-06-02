package com.dilsonjlrjr.javatrellodashboardmateus.config;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.EntityNotFoundException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.SecurityResourceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.ServiceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumMainControllerAdviceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumMainControllerAdviceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.HttpErrorDtoResponse;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class MainControllerAdviceConfig {

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleExpiredJwtException(ExpiredJwtException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.FORBIDDEN.value())
                        .code(EnumMainControllerAdviceCode.EXPIRED_TOKEN.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.FORBIDDEN.value())
                        .code(EnumMainControllerAdviceCode.BAD_CREDENTIALS.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleHttpException(HttpClientErrorException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(ex.getStatusCode().value())
                        .code(EnumMainControllerAdviceCode.HTTP_CLIENT_ERROR_EXCEPTION.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleCustomInternalServerError(Exception ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .code(EnumMainControllerAdviceCode.IO_EXCEPTION.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<HttpErrorDtoResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        List<FieldError> rawData = ex.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = rawData.stream()
                .collect(
                        Collectors.toMap(   FieldError::getField,
                                            (item -> item.getDefaultMessage() != null ? item.getDefaultMessage() : "Unknown error"), (o, o2) -> o));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .code(EnumMainControllerAdviceCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode())
                        .data(errorMap)
                        .message(EnumMainControllerAdviceMessage.FIELD_NOT_VALID.getMessage()).build());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<HttpErrorDtoResponse> handleConstraintViolation(ConstraintViolationException ex) {
        log.error(ex.getMessage());

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .code(EnumMainControllerAdviceCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode())
                        .data(errors)
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleInternalAuthenticationServiceException(RuntimeException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.UNAUTHORIZED.value())
                        .code(EnumMainControllerAdviceCode.USER_NOT_FOUND_BAD_CREDENTIALS.getCode())
                        .message(EnumMainControllerAdviceMessage.USER_NOT_FOUND_BAD_CREDENTIALS.getMessage()).build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleRunTimeException(RuntimeException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .code(EnumMainControllerAdviceCode.RUNTIME_EXCEPTION.getCode())
                        .message(EnumMainControllerAdviceMessage.GENERIC_ERROR.getMessage()).build());
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleServiceException(ServiceException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .code(ex.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({SecurityResourceException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleServiceResourceException(SecurityResourceException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.UNAUTHORIZED.value())
                        .code(ex.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .code(ex.getCode())
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .code(null)
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .code(null)
                        .message(ex.getMessage()).build());
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<HttpErrorDtoResponse> handleDuplicateKeyException(DuplicateKeyException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(HttpErrorDtoResponse.builder()
                        .httpStatus(HttpStatus.CONFLICT.value())
                        .code("DUPLICATE KEY")
                        .message("Record already exists in database!").build());
    }
}
