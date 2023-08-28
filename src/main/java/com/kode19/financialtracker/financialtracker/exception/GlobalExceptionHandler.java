package com.kode19.financialtracker.financialtracker.exception;

import static com.kode19.financialtracker.financialtracker.exception.response.ErrorCode.DATA_NOT_FOUND;
import static com.kode19.financialtracker.financialtracker.exception.response.ErrorCode.INTERNAL_ERROR;
import static com.kode19.financialtracker.financialtracker.exception.response.ErrorCode.INVALID_DATA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.kode19.financialtracker.financialtracker.exception.custom_exception.DataNotAvailableException;
import com.kode19.financialtracker.financialtracker.exception.custom_exception.ErrorDataProcessingException;
import com.kode19.financialtracker.financialtracker.exception.response.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  private final MessageService messageService;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request) {
    log.error(ex.getMessage());
    List<String> message = List.of(messageService.getMessage("messages.error.internal"));
    ErrorResponseDTO error = new ErrorResponseDTO(INTERNAL_SERVER_ERROR.value(), message,
        INTERNAL_ERROR, request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));
    return ResponseEntity.internalServerError().body(error);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex,
      HttpServletRequest request) {
    log.error(ex.getMessage());
    List<String> message = List.of(messageService.getMessage("messages.error.internal"));
    ErrorResponseDTO error = new ErrorResponseDTO(INTERNAL_SERVER_ERROR.value(), message,
        INTERNAL_ERROR, request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));
    return ResponseEntity.internalServerError().body(error);
  }

  @ExceptionHandler(ErrorDataProcessingException.class)
  public ResponseEntity<Object> handleErrorDataProcessingException(ErrorDataProcessingException ex,
      HttpServletRequest request) {
    List<String> message = List.of(ex.getMessage());
    ErrorResponseDTO error = new ErrorResponseDTO(BAD_REQUEST.value(), message, INVALID_DATA,
        request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(DataNotAvailableException.class)
  public ResponseEntity<Object> handleDataNotAvailableException(DataNotAvailableException ex,
      HttpServletRequest request) {
    List<String> message = List.of(ex.getMessage());
    ErrorResponseDTO error = new ErrorResponseDTO(NOT_FOUND.value(), message, DATA_NOT_FOUND,
        request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(error, NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
        .map(String::toUpperCase)
        .toList();

    ErrorResponseDTO error = new ErrorResponseDTO(BAD_REQUEST.value(), errors, INVALID_DATA,
        request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(error, BAD_REQUEST);
  }

}
