package com.example.svelte.practice.web.config.advice;

import com.example.svelte.practice.domain.service.LoginService;
import com.example.svelte.practice.web.config.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {

    private final LoginService loginService;

    // @RequestBody @Valid 사용 시 해당 객체에 설정되어 있는 검증과 다를 시 예외가터짐.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> messageConverterValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(400,"입력값을 충족시키지 못했습니다.", e.getBindingResult());
        log.info("messageConverterValidException");
        log.info("{}", errorResponse.getErrors());
        log.info("{}", errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // @Valid 만 사용 시 해당 객체에 설정되어 있는 검증과 다를 시 예외가터짐.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> bindExceptionError(BindException e) {
        ErrorResponse errorResponse = new ErrorResponse(400, "입력값을 충족시키지 못했습니다.", e.getBindingResult());
        log.info("bindExceptionError");
        log.info("{}", errorResponse.getErrors());
        log.info("{}", errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> MemberNotFoundExceptionError() {
        ErrorResponse errorResponse = new ErrorResponse(400, "아이디 또는 비밀번호가 맞지 않습니다.");
        log.info("MemberNotFoundExceptionError");
        log.info("{}", errorResponse.getErrors());
        log.info("{}", errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

