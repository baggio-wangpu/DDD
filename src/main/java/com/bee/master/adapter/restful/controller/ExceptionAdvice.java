package com.bee.master.adapter.restful.controller;

import com.bee.master.application.response.ErrorResponse;
import com.bee.master.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(BaseException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseEntity.status(exception.getStatus()).body(ErrorResponse.from(exception));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Throwable throwable) {
        log.error(throwable.getMessage());
        return ErrorResponse.from(BaseException.unknown(throwable));
    }

}
